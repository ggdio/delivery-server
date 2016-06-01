(function($) {
    jQuery(document).ready(function($) {

        /* Creation of additional elements
	    ================================================== */
        $('.section').wrapInner('<article class="contents"></article>');
        $('.section').append('<div class="half-pattern left"><div class="rotate"></div></div><div class="half-pattern right"><div class="rotate"></div></div>');
        $('.next-section').wrapInner('<span></span>');
        $('h2,h3').wrapInner('<div class="headline"></div>');
        $('.headline').append('<div class="pattern-left"><span class="icon-polygon"></span></div><div class="pattern-right"><span class="icon-polygon"></span></div>');
        $('.grid .item div.wow').wrapInner('<div class="rotate-left"></div>').wrapInner('<div class="rotate-right"></div>').wrapInner('<div class="filter-item"></div>');
        $('.vertical-separator').wrapInner('<span></span>');
        $('.vertical-separator').each(function(i) {
            var height = $(this).parents(".row").height();
            $('span', this).css('height', height - 50);
        });
        $('.entry-meta-details span').append('<div class="line-decoration hidden-xs"></div>');
        $('.progress-bar').each(function(i) {
            var value = $(this).data('value');
            $('span', this).css('width', value+'%');
        });

        /* Default settings: scrollbox
	    ================================================== */
        $('.scrollbox').scrollbox({

            direction: 'h',
            linear: true,
            step: 1,
            delay: 0,
            speed: 50

        });

        /* Default settings: owl carousel
	    ================================================== */
        $(".owl-carousel").owlCarousel({

            slideSpeed: 300,
            paginationSpeed: 400,
            singleItem: true

        });

        /* Up arrow when scrolling
        ================================================== */
        var offset = 220;
        var duration = 500;
        $(window).scroll(function() {
            if ($(this).scrollTop() > offset) {
                $('.back-to-top').fadeIn(duration);
            } else {
                $('.back-to-top').fadeOut(duration);
            }
        });

        $('.back-to-top').on( "click", function(event) {
            event.preventDefault();
            $('html, body').animate({scrollTop: 0}, duration);
            return false;
        });

        /* initialize shuffle plugin (jquery.shuffle.min.js)
	    ================================================== */
        var $grid = $('.grid');

        $grid.shuffle({
            itemSelector: '.item' // the selector for the items in the grid
        });

        $('.filter a').on( "click", function(event) {

            event.preventDefault();
            $('.filter a').removeClass('active');
            $(this).addClass('active');
            var groupName = $(this).attr('data-group');
            $grid.shuffle('shuffle', groupName);

        });

        // contact form
        $("#ajax-contact-form").submit(function() {
            var str = $(this).serialize();
            $.ajax({
                type: "POST",
                url: "php/contact-form.php",
                data: str,
                success: function(msg) {
                    if(msg == 1) {
                        result = '<div class="alert success fade in">Your message has been sent. Thank you!<a href="#" class="close-alert" data-dismiss="alert"></a></div>';
                        $("#ajax-contact-form").hide();
                    } else {result = msg;}
                    $('#form-message').hide();
                    $('#form-message').html(result);
                    $('#form-message').fadeIn("slow");
                    $('html, body').animate({ 
                        scrollTop: $('#form-message').offset().top - 130 
                    },1500);
                }
            });
            return false;
        });

    });
    $(function() {
        /* Position header
	    ================================================== */
        var scroll = $(document).scrollTop();
        var headerHeight = $('#header').outerHeight();

        $(window).scroll(function() {
            // scrolled is new position just obtained
            var scrolled = $(document).scrollTop();

            // optionally emulate non-fixed positioning behaviour

            if (scrolled > headerHeight) {
                $('#header').addClass('off-canvas');
            } else {
                $('#header').removeClass('off-canvas');
            } 

            if (scrolled > scroll) {
                // scrolling down
                $('#header').removeClass('fixed');
            } else {
                //scrolling up
                $('#header').addClass('fixed');
            }

            scroll = $(document).scrollTop();
        });

        /* Navigation
        ================================================== */
        $('.nav a,.next-section').on('click', function(e) {
            
            e.preventDefault();
            $link = $(this).attr('href');
 
            $('html, body').animate({ 
                scrollTop: $($link).offset().top - 130 
            },1500);
                
            var panelSecondConfig = {latency: 100};
            $(window).on('scrollstop', panelSecondConfig, function() {
                $('#header').addClass('fixed');
            }); 

        });

        /* Decorative line in the blog post
	    ================================================== */
        $(window).load(function() {
            $('.entry-meta-details').each(function(i) {
                var width = $(this).width();
                var width_details = $('span', this).width();
                var width_comments = $('.entry-comments').width();
                $('.line-decoration', this).css('width', width - width_details - width_comments - 10);
            });
        });

    });

    /* Initialize swipebox plugin (jQuery lightbox plugin)
    ================================================== */
    $( '.swipebox' ).swipebox( {
        useCSS : false, // false will force the use of jQuery for animations
        useSVG : false, // false to force the use of png for buttons
        initialIndexOnArray : 0, // which image index to init when a array is passed
        hideCloseButtonOnMobile : false, // true will hide the close button on mobile devices
        hideBarsDelay : 0, // delay before hiding bars on desktop
        videoMaxWidth : 1140, // videos max widthg
        afterOpen: null, // called after opening
        loopAtEnd: false // true will return to the first image after the last image is reached
    } );

    /* WOW plugin triggers animation.css on scroll
    ================================================== */
    new WOW({
        boxClass: 'wow', // animated element css class (default is wow)
        offset: 0, // distance to the element when triggering the animation (default is 0)
        mobile: false // trigger animations on mobile devices (true is default)
    }).init();

})(jQuery);