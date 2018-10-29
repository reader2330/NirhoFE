$(document).ready(function () {
    $(".btn-select").each(function (e) {
        var value = $(this).find("ul li.selected").html();
        if (value != undefined) {
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
    });

    $('.sidebar-toggle').click(function () {
        if ($('#salud').hasClass('salud')) {
            $('#salud').addClass('ocultar');
            $("#salud").removeClass('salud');
        } else {
            if ($('#salud').hasClass('ocultar')) {
                $('#salud').removeClass('ocultar');
                $('#salud').addClass('salud');
            }
        }
    });
});

$(document).on('click', '.btn-select', function (e) {
    e.preventDefault();
    var ul = $(this).find("ul");
    if ($(this).hasClass("active")) {
        if (ul.find("li").is(e.target)) {
            var target = $(e.target);
            target.addClass("selected").siblings().removeClass("selected");
            var value = target.html();
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
        ul.hide();
        $(this).removeClass("active");
    } else {
        $('.btn-select').not(this).each(function () {
            $(this).removeClass("active").find("ul").hide();
        });
        ul.slideDown(300);
        $(this).addClass("active");
    }
});

$(document).on('click', function (e) {
    var target = $(e.target).closest(".btn-select");
    if (!target.length) {
        $(".btn-select").removeClass("active").find("ul").hide();
    }
});


/** add active class and stay opened when selected */
var url = window.location;
// for sidebar menu entirely but not cover treeview
$('ul.sidebar-menu a').filter(function () {
    return this.href == url;
}).parent().addClass('active');
// for treeview
$('ul.treeview-menu a').filter(function () {
    return this.href == url;
}).parentsUntil(".sidebar-menu > .treeview-menu").addClass('active');
