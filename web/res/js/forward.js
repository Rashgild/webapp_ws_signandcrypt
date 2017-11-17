/**
 * Created by rkurbanov on 11.08.2017.
 */

function forwardWithotClick(aLink) {
    $.get("ForwardServlet", {Link: aLink}, function (response) {
        $(".content").html(response);
    });
}

function forwardbyClick(clicked, aLink) {
    $(clicked).click(function(){
        forwardWithotClick(aLink);
    });
}

function forwardbyLinkWithoutClick(Link,todiv) {
    $.ajax({
        type:"POST",
        url: Link,
        success: function (response){
            $(todiv).html(response);
        }
    });
}
function forwardbyLink(clicked,Link) {
    $(document).on("click", clicked, function() {
        forwardbyLinkWithoutClick(Link);
    });
}

function relocetionOnClick(clicked,Link) {
    $(document).one("click", clicked, function() {
        $(location).attr('href',"/"+Link);
    });
}