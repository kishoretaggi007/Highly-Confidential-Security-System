$(function(){
	$('header').click(function(){
		$(this).find('#menu-top-navigation li:last-child a').attr('target','_blank');
	});

	setTimeout(function(){
		$('#masthead ul').children('li').first().delay(2000).fadeIn(0).fadeIn({
			duration:700,
			easing:'easeOutBack',
			complete:function(){
				$(this).next().delay(300).fadeIn({
					duration:700,
					easing:'easeOutBack',
					complete: arguments.callee
				});
			}
		});
	});


	$('#wheel').cycle({
		timeout:3000,
		speed:3000,
		fx:'scrollHorz',
		prev:'.left',
		next:'.right',
		pager:'#logos, #dots',
		pagerAnchorBuilder:function(idx, slide){
			return '#logos a:eq(' + idx + '), #dots a:eq(' + idx + ')';
		}
	});


	$("#zip").keypress(function(){
		return /\d/.test(String.fromCharCode(event.keyCode));
    });


	$('.strategy #content h4').next('p').hide().next('ul').hide();
//	$('.strategy #content h4:first-child').addClass('open').next('p').show();
	$('.strategy #content h4').click(function(){
		$(this).toggleClass('open');
		$(this).nextUntil('h4').slideToggle({
			duration:1000,
			easing:'easeOutBounce'
		});
	});


	$('.portfolio #side ul li a').click(function(){
		$(this).toggleClass('open');
		$(this).next('div').slideToggle({duration:1000, easing:'easeOutBounce'});
		return false;
	});


	$('.news').parents().find('header li a:contains("News")').parent('li').addClass('current-menu-item');
	var baseUrl = 'http://www.assetman.com';
	$('.news').parents().find('header li a:contains("News")').each(function(){
		var href = $(this).attr('href');
		if (href == baseUrl + document.location.pathname) {
			$('.news #side ul li:first-child').addClass('current-archive-item');
		}
	});
	$('.news #side ul li a').each(function(){
		var href = $(this).attr('href');
		if (href == baseUrl + document.location.pathname) {
			$(this).parent('li').addClass('current-archive-item');
		}
	});


	$('.directions').click(function(){
			if ($(this).hasClass('sanfran')){
				$('#sanfran').fadeIn(1000);
				$('.contact figure').not('#sanfran').hide();
			}
			else if ($(this).hasClass('sanjose')){
				$('#sanjose').fadeIn(1000);
				$('.contact figure').not('#sanjose').hide();
			}
			else if ($(this).hasClass('sandhill')){
				$('#sandhill').fadeIn(1000);
				$('.contact figure').not('#sandhill').hide();
			}
			else if ($(this).hasClass('pagemill')){
				$('#pagemill').fadeIn(1000);
				$('.contact figure').not('#pagemill').hide();
			}
		$('#location').fadeTo(0, 0.5, function(){
			$('#lightbox.contact').fadeIn(1000).css('display','table');
		});
	});

	$('.close').click(function(){
		$('#lightbox.contact').fadeOut(200).find('figure').fadeOut(200, function(){
			$('#location').fadeTo(200, 1);
		});
	});


	$('.portfolio #side ul li a').click(function(){
		$(this).toggleClass('open');
		$(this).find('div').slideToggle({
			duration:1000,
			easing:'easeOutBounce'
		});
		return false;
	});

	$('.portfolio figure div').click(function(){
		$(this).clone().appendTo('#lightbox.portfolio figure');
		$(this).parents('.portfolio #content').css('opacity','0.2');
		$('#lightbox.portfolio').fadeIn(1000).css('display','table');
		return false;
	});

	$('#lightbox.portfolio .close').click(function(){
		$('#lightbox.portfolio').fadeOut(1000, function(){
			$('#lightbox.portfolio #center figure div').detach();
		});
		$('.portfolio #content').animate({opacity:1}, 1000);
	});


	$('.team.all figure').click(function(){
		var name = $(this).children('figcaption').text();
		var link = $('.team #side ul li a:contains("'+name+'")').attr('href');
		window.location = link;
	});


	$('.news #content, .team.profile #content figure, #lightbox.portfolio figure').click(function(){
		$(this).find('a').attr('target','_blank');
	});


	$('.pager').click(function() {
		$("html, body").delay(500).animate({
			scrollTop:0
		});
	});
});


//window.addEventListener('load', function() {
//	setTimeout(scrollTo, 0, 0, 0);
//}, false);

function scrollTo() {
	setTimeout(scrollTo, 0, 0, 0);
}
if (window.addEventListener) {
	window.addEventListener('load', scrollTo, false);
}
else if (window.attachEvent) {
	window.attachEvent('onload', scrollTo);
} else if ( window.onLoad ) {
	window.onload = scrollTo;
}
