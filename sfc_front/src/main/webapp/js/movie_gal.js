// JavaScript Document
$(document).ready(function(){
	$('.scroll_right').click(function(){
		if($('.scroll_content').is(':animated')){
			$('.scroll_content').stop(true,true);
		}/* 避免点击事件重复 */
		jQuery('.scroll_left').css('background','url("/images/side_icon.png") no-repeat -180px -1127px');
		ml = parseInt($('.scroll_content').css('left'));
		r = liw - (790 - ml);  /* 1000为外部区块.infopic的宽度，15为li之间的距离，即.piclist li的margin-right的值 */
		if(r<790){
			s = r;
			jQuery('.scroll_right').css('background','url("/images/side_icon.png") no-repeat -220px -1127px');
		}else{
			s = 790;
		}
		$('.scroll_content').animate({left: ml - s + 'px'},'slow');			
	})
	
	$('.scroll_left').click(function(){
		
		if($('.scroll_content').is(':animated')){
			$('.scroll_content').stop(true,true);
		}/* 避免点击事件重复 */
		jQuery('.scroll_right').css('background','url("/images/side_icon.png") no-repeat -220px -1093px');
		ml = parseInt($('.scroll_content').css('left'));
		if(ml>-790){
			s = ml;
			jQuery('.scroll_left').css('background','url("/images/side_icon.png") no-repeat -180px -1093px');
		}else{
			s = -790;
		}
		$('.scroll_content').animate({left: ml - s + 'px'},'slow');			
	})
	
})
//info pic
$(window).load(function(){
	liw = 0;
	$('.scroll_content a').each(function(){
		liw += $(this).width() + 11;
		$(this).css('width',$(this).width()+'px');
	});
	$('.scroll_content').width( liw + 'px');
});
