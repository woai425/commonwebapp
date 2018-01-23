(function() {
	var d = function() {
	};
	function a(g) {
		this.data = g.path || g.data;
		this.imageData = [];
		this.multiplier = g.multiplier || 1;
		this.padding = g.padding || 0;
		this.fps = g.fps || 25;
		this.stepsPerFrame = ~~g.stepsPerFrame || 1;
		this.trailLength = g.trailLength || 1;
		this.pointDistance = g.pointDistance || 0.05;
		this.domClass = g.domClass || "sonic";
		this.fillColor = g.fillColor || "#FFF";
		this.strokeColor = g.strokeColor || "#FFF";
		this.stepMethod = typeof g.step == "string" ? b[g.step] : g.step
				|| b.square;
		this._setup = g.setup || d;
		this._teardown = g.teardown || d;
		this._preStep = g.preStep || d;
		this.width = g.width;
		this.height = g.height;
		this.fullWidth = this.width + 2 * this.padding;
		this.fullHeight = this.height + 2 * this.padding;
		this.domClass = g.domClass || "sonic";
		this.setup()
	}
	var f = a.argTypes = {
		DIM : 1,
		DEGREE : 2,
		RADIUS : 3,
		OTHER : 0
	};
	var e = a.argSignatures = {
		arc : [ 1, 1, 3, 2, 2, 0 ],
		bezier : [ 1, 1, 1, 1, 1, 1, 1, 1 ],
		line : [ 1, 1, 1, 1 ]
	};
	var c = a.pathMethods = {
		bezier : function(z, n, l, h, g, p, o, k, j) {
			z = 1 - z;
			var m = 1 - z, w = z * z, u = m * m, v = w * z, s = 3 * w * m, r = 3
					* z * u, q = u * m;
			return [ v * n + s * p + r * k + q * h,
					v * l + s * o + r * j + q * g ]
		},
		arc : function(l, i, n, h, m, j) {
			var g = (j - m) * l + m;
			var k = [ (Math.cos(g) * h) + i, (Math.sin(g) * h) + n ];
			k.angle = g;
			k.t = l;
			return k
		},
		line : function(i, k, j, h, g) {
			return [ (h - k) * i + k, (g - j) * i + j ]
		}
	};
	var b = a.stepMethods = {
		square : function(g, j, k, h, l) {
			this._.fillRect(g.x - 3, g.y - 3, 6, 6)
		},
		fader : function(g, j, k, h, l) {
			this._.beginPath();
			if (this._last) {
				this._.moveTo(this._last.x, this._last.y)
			}
			this._.lineTo(g.x, g.y);
			this._.closePath();
			this._.stroke();
			this._last = g
		}
	};
	a.prototype = {
		setup : function() {
			var o, q, h, u, k = this.data;
			this.canvas = document.createElement("canvas");
			this._ = this.canvas.getContext("2d");
			this.canvas.className = this.domClass;
			this.canvas.height = this.fullHeight;
			this.canvas.width = this.fullWidth;
			this.points = [];
			for (var m = -1, j = k.length; ++m < j;) {
				o = k[m].slice(1);
				h = k[m][0];
				if (h in e) {
					for (var s = -1, n = o.length; ++s < n;) {
						q = e[h][s];
						u = o[s];
						switch (q) {
						case f.RADIUS:
							u *= this.multiplier;
							break;
						case f.DIM:
							u *= this.multiplier;
							u += this.padding;
							break;
						case f.DEGREE:
							u *= Math.PI / 180;
							break
						}
						o[s] = u
					}
				}
				o.unshift(0);
				for (var g, p = this.pointDistance, v = p; v <= 1; v += p) {
					v = Math.round(v * 1 / p) / (1 / p);
					o[0] = v;
					g = c[h].apply(null, o);
					this.points.push({
						x : g[0],
						y : g[1],
						progress : v
					})
				}
			}
			this.frame = 0
		},
		prep : function(g) {
			if (g in this.imageData) {
				return
			}
			this._.clearRect(0, 0, this.fullWidth, this.fullHeight);
			var q = this.points, o = q.length, m = this.pointDistance, p, k, n;
			this._setup();
			for (var j = -1, h = o * this.trailLength; ++j < h && !this.stopped;) {
				k = g + j;
				p = q[k] || q[k - o];
				if (!p) {
					continue
				}
				this.alpha = Math.round(1000 * (j / (h - 1))) / 1000;
				this._.globalAlpha = this.alpha;
				this._.fillStyle = this.fillColor;
				this._.strokeStyle = this.strokeColor;
				n = g / (this.points.length - 1);
				indexD = j / (h - 1);
				this._preStep(p, indexD, n);
				this.stepMethod(p, indexD, n)
			}
			this._teardown();
			this.imageData[g] = (this._.getImageData(0, 0, this.fullWidth,
					this.fullWidth));
			return true
		},
		draw : function() {
			if (!this.prep(this.frame)) {
				this._.clearRect(0, 0, this.fullWidth, this.fullWidth);
				this._.putImageData(this.imageData[this.frame], 0, 0)
			}
			this.iterateFrame()
		},
		iterateFrame : function() {
			this.frame += this.stepsPerFrame;
			if (this.frame >= this.points.length) {
				this.frame = 0
			}
		},
		play : function() {
			this.stopped = false;
			var g = this;
			this.timer = setInterval(function() {
				g.draw()
			}, 1000 / this.fps)
		},
		stop : function() {
			this.stopped = true;
			this.timer && clearInterval(this.timer)
		}
	};
	window.Sonic = a
}());

var currentThemeWaitingImageColor = '#FFFFFF';
var gridThemeWaitingImageColor = '#000000';
var loaders = [ {
	width : 100,
	height : 100,
	
	stepsPerFrame : 1,
	trailLength : 1,
	pointDistance : .05,
	step : 'fader',

	strokeColor : currentThemeWaitingImageColor,

	fps : 15,

	setup : function() {
		this._.lineWidth = 2;
	},
	step : function(point, index) {

		var cx = this.padding + 50, cy = this.padding + 50, _ = this._, angle = (Math.PI / 180)
				* (point.progress * 360), innerRadius = index === 1 ? 100 : 25;

		_.beginPath();
		_.arc(point.x, point.y, 1, 0, 360, 0);
		_.closePath();
		_.stroke();
	},
	path : [ [ 'arc', 50, 50, 25, 0, 360 ] ]
},{
	width : 100,
	height : 100,
	stepsPerFrame : 1,
	trailLength : 1,
	pointDistance : .05,
	step : 'fader',

	strokeColor : gridThemeWaitingImageColor,

	fps : 15,

	setup : function() {
		this._.lineWidth = 2;
	},
	step : function(point, index) {

		var cx = this.padding + 50, cy = this.padding + 50, _ = this._, angle = (Math.PI / 180)
				* (point.progress * 360), innerRadius = index === 1 ? 100 : 25;

		_.beginPath();
		_.arc(point.x, point.y, 1, 0, 360, 0);
		_.closePath();
		_.stroke();
	},
	path : [ [ 'arc', 50, 50, 25, 0, 360 ] ]
}];
function canvasLoading(){
	var div, loading;
	var container = $("#portalWaitingContainer");
	var loadingPanel = $("#portalWaitingContainer > .portalWaitingCanvas");
	if (loadingPanel.length == 0) {
		div = document.createElement('div');
		div.className = 'portalWaitingCanvas';
		loading = new Sonic(loaders[0]);
		div.appendChild(loading.canvas);
		container[0].appendChild(div);
		loading.play();
	} 	
}