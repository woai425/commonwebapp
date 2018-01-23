<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/layout/lib/jquery/jquery-1.8.3.js"></script>
<style type="text/css">
body {
	  background-color: #01aaff;
	}

	::-webkit-scrollbar {
	  width: 8px;
	}

	::-webkit-scrollbar-track {
	  background: #01AAFF;
	}

	::-webkit-scrollbar-thumb {
	  background: #01AAFF;
	}

	::-webkit-scrollbar-thumb:hover {
	  background: #fff;
	}

</style>
</head>
<body>
<canvas id="waves" width="419" height="592" style="width: 419px; height: 592px;"></canvas>
<style>
body {
  background-color: #3498DB;
}

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: #3498DB;
}

::-webkit-scrollbar-thumb {
  background: #3498DB;
}

::-webkit-scrollbar-thumb:hover {
  background: #fff;
}
</style>
<script>/**
 * Generates multiple customizable animated sines waves
 * using a canvas element. Supports retina displays and
 * limited mobile support
 *
 * I've created a seperate library based on this pen. 
 * Check it out at https://github.com/isuttell/sine-waves
 */
function SineWaveGenerator(options) {
  $.extend(this, options || {});

  if (!this.el) {
    throw "No Canvas Selected";
  }
  this.ctx = this.el.getContext('2d');

  if (!this.waves.length) {
    throw "No waves specified";
  }

  // Internal
  this._resizeWidth();
  window.addEventListener('resize', this._resizeWidth.bind(this));
  // User
  this.resizeEvent();
  window.addEventListener('resize', this.resizeEvent.bind(this));

  if (typeof this.initialize === 'function') {
    this.initialize.call(this);
  }
  // Start the magic
  this.loop();
}

// Defaults
SineWaveGenerator.prototype.speed = 10;
//SineWaveGenerator.prototype.amplitude = 50;
SineWaveGenerator.prototype.wavelength = 50;
SineWaveGenerator.prototype.segmentLength = 10;

SineWaveGenerator.prototype.lineWidth = 2;
SineWaveGenerator.prototype.strokeStyle = 'rgba(255, 255, 255, 0.2)';

SineWaveGenerator.prototype.resizeEvent = function() {};

// fill the screen
SineWaveGenerator.prototype._resizeWidth = function() {
  this.dpr = window.devicePixelRatio || 1;

  this.width = this.el.width = window.innerWidth * this.dpr;
  this.height = this.el.height = window.innerHeight * this.dpr;
  this.el.style.width = window.innerWidth + 'px';
  this.el.style.height = window.innerHeight + 'px';

  this.waveWidth = this.width * 0.95;
  this.waveLeft = this.width * 0;
}

SineWaveGenerator.prototype.clear = function() {
  this.ctx.clearRect(0, 0, this.width, this.height);
}

SineWaveGenerator.prototype.time = 0;

SineWaveGenerator.prototype.update = function(time) {
  this.time = this.time - 0.007;
  if (typeof time === 'undefined') {
    time = this.time;
  }

  var index = -1;
  var length = this.waves.length;

  while (++index < length) {
    var timeModifier = this.waves[index].timeModifier || 1;
    this.drawSine(time * timeModifier, this.waves[index]);
  }
  index = void 0;
  length = void 0;
}

// Constants
var PI2 = Math.PI * 2;
var HALFPI = Math.PI / 2;

SineWaveGenerator.prototype.ease = function(percent, amplitude) {
  return amplitude * (Math.sin(percent * PI2 - HALFPI) + 1) * 0.5;
}

SineWaveGenerator.prototype.drawSine = function(time, options) {
  options = options || {};
  amplitude = options.amplitude || this.amplitude;
  wavelength = options.wavelength || this.wavelength;
  lineWidth = options.lineWidth || this.lineWidth;
  strokeStyle = options.strokeStyle || this.strokeStyle;
  segmentLength = options.segmentLength || this.segmentLength;

  var x = time;
  var y = 0;
  var amp = this.amplitude;

  // Center the waves
  var yAxis = this.height / 2;

  // Styles
  this.ctx.lineWidth = lineWidth * this.dpr;
  this.ctx.strokeStyle = strokeStyle;
  this.ctx.lineCap = 'round';
  this.ctx.lineJoin = 'round';
  this.ctx.beginPath();

  // Starting Line
  this.ctx.moveTo(0, yAxis);
  this.ctx.lineTo(this.waveLeft, yAxis);

  for (var i = 0; i < this.waveWidth; i += segmentLength) {
    x = (time * this.speed) + (-yAxis + i) / wavelength;
    y = Math.sin(x);

    // Easing
    amp = this.ease(i / this.waveWidth, amplitude);

    this.ctx.lineTo(i + this.waveLeft, amp * y + yAxis);

    amp = void 0;
  }

  // Ending Line
  this.ctx.lineTo(this.width, yAxis);

  // Stroke it
  this.ctx.stroke();

  // Clean up
  options = void 0;
  amplitude = void 0;
  wavelength = void 0;
  lineWidth = void 0;
  strokeStyle = void 0;
  segmentLength = void 0;
  x = void 0;
  y = void 0;
}

SineWaveGenerator.prototype.loop = function() {
  this.clear();
  this.update();

  window.requestAnimationFrame(this.loop.bind(this));
}

new SineWaveGenerator({
  el: document.getElementById('waves'),

  speed: 8,

  waves: [{
    timeModifier: 0.5,
    lineWidth: 1,
    amplitude: 12,
    wavelength: 200,
    segmentLength: 20,
    //       strokeStyle: 'rgba(255, 255, 255, 0.5)'
  }, {
    timeModifier: 0.5,
    lineWidth: 1,
    amplitude: -12,
    wavelength: 50,
    segmentLength: 10,
    //       strokeStyle: 'rgba(255, 255, 255, 0.2)'
  }],

  initialize: function() {

  },

  resizeEvent: function() {
    var gradient = this.ctx.createLinearGradient(0, 0, this.width, 0);
    gradient.addColorStop(0, "rgba(254, 255, 255, 0)");
    gradient.addColorStop(0.5, "rgba(255, 255, 255, 1)");
    gradient.addColorStop(1, "rgba(255, 255, 254, 0)");

    var index = -1;
    var length = this.waves.length;
    while (++index < length) {
      this.waves[index].strokeStyle = gradient;
    }

    // Clean Up
    index = void 0;
    length = void 0;
    gradient = void 0;
  }
});
</script>
</body>
</html>