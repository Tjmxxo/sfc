;(function ($) {
    $.fn.extend({
        komovie_select: function (options) {
            var defaults = {
                width: 100,
                callback: function () {
                }
            };
            var opts = $.extend({}, defaults, options);
            return this.each(function () {
                var $this = $(this);
                $this.hide();
                var source_tip = $("<div>").addClass("source_tip");
                var source_select = $("<div>").addClass("source_select").append('<font></font><p></p>');
                var source_key = $("<div>").addClass("source_key");
                source_select.bind("click", function (e) {
                    e.stopPropagation();
                    source_key.toggle();
                })
                $(document).bind("click", function () {
                    source_key.hide();
                })
                for (var i = 0; i < $this.children("option").length; i++) {
                    var item = $this.children("option").eq(i);
                    var label = $("<label>").attr("val", item.attr("value")).html(item.html());
                    label.bind("click", function (e) {
                        e.stopPropagation();
                        source_select.find("font").html($(this).html());
                        $(this).addClass("current_select").siblings().removeClass("current_select");
                        source_tip.prev().val($(this).attr("val"));
                        source_key.hide();
                        opts.callback();
                    })
                    source_key.append(label);
                }
                source_select.find("font").width(opts.width);
                source_key.width(opts.width + 41);
                if (!$this.attr("val") || source_key.find("label[val=" + $this.attr("val") + "]").length == 0) {
                    source_key.find("label").eq(0).click();
                } else {
                    source_key.find("label[val=" + $this.attr("val") + "]").eq(0).click();
                }
                source_tip.append(source_select);
                source_tip.append('<div class="clearfix"></div>');
                source_tip.append(source_key);
                source_tip.insertAfter($this);
            })
        }
    });
})(jQuery);