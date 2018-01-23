/**
 *	Toggles
 *
 *	Non-animation
 */
 

(function($, window, undefined){
	
	$(document).ready(function(){
		// Panel Close
		$('body').on('click', '.panel a[data-toggle="remove"]', function(ev){
			ev.preventDefault();
			var $panel = $(this).closest('.panel'),
				$panel_parent = $panel.parent();
			$panel.remove();
			if($panel_parent.children().length == 0){
				$panel_parent.remove();
			}
		});
		
		// Panel Reload
		$('body').on('click', '.panel a[data-toggle="reload"]', function(ev){
			ev.preventDefault();
			var $panel = $(this).closest('.panel');
			$panel.append('<div class="panel-disabled"><div class="loader-1"></div></div>');
			var $pd = $panel.find('.panel-disabled');
			setTimeout(function(){
				$pd.fadeOut('fast', function(){
					$pd.remove();
				}); 
			}, 500 + 300 * (Math.random() * 5));
		});
		
		// Panel Expand/Collapse Toggle
		$('body').on('click', '.panel a[data-toggle="panel"]', function(ev){
			ev.preventDefault();
			var $panel = $(this).closest('.panel');
			$panel.toggleClass('collapsed');
		});	
	});
	
})(jQuery, window);