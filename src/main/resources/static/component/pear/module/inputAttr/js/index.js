/**
 * @name inputAttr
 * @author HuangJunjie
 * @description 给 input 附加更多实用的 attribute
 * @version 2.0.0.20210818
 */

layui.define(["layer", "jquery"], function (exports) {
    var $ = layui.jquery;
    var prefix = "input-attr";

    layui.link(layui.cache.base + "inputAttr/css/theme.css");

    /**
     * 检查是否有某个属性
     * @param dom
     * @param attr
     * @returns {boolean}
     */
    function hasAttr(dom, attr) {
        var attrs = dom.attributes;

        for (var i = 0; i < attrs.length; i++) {
            if (attrs[i].name === attr) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取标签的名字
     * @param dom
     * @returns {string}
     */
    function getTagName(dom) {
        return dom.tagName.toLowerCase();
    }

    /**
     * 创建扩展自定义的样式, 防止冲突
     * @param name
     * @returns {string}
     */
    function creatClassList(name) {
        return [prefix, name].join("-");
    }

    /**
     * 初始化
     * @param opts
     */
    function render(opts) {
        if (!opts) opts = {};

        // 根节点
        var $root = $(opts.root || document);

        // 匹配表单节点
        var $doms = $root.find(opts.matchRule || "input,textarea");

        // 事件托管
        function activeEvent(name, event, data) {
            if (opts.event && typeof opts.event[name] == "function") {
                opts.event[name](event, data);
            }
        }

        // 开始处理
        $doms.each(function () {
            var hasClearable = hasAttr(this, "clearable");
            var showWordLimit = hasAttr(this, "show-word-limit");
            var tagName = getTagName(this);

            var $el = $(this);
            var $target = $el.parent();
            var maxlength = parseInt($(this).attr("maxlength")) || 0;
            $target.addClass(creatClassList(tagName));

            if (hasClearable && tagName === "input") {
                $el.addClass(creatClassList("allow-clearable"));

                var $i = $('<i class="layui-icon layui-icon-close-fill"></i>');
                $i.addClass(creatClassList("close-icon"));
                $target.append($i);

                function toggle() {
                    $i.css({ opacity: $el.val() ? 1 : 0 });
                }

                toggle();

                $i.on("click", function (e) {
                    e.stopPropagation();
                    $i.css({ opacity: 0 });
                    $el.val("").focus();
                    activeEvent("clearable", e, $el);
                });

                $el.on("input", toggle)
                    .on("focus", toggle)
                    .on("blur", function (e) {
                        $i.css({ opacity: 0 });
                    });

                return;
            }

            if (showWordLimit && maxlength > 0) {
                $el.addClass(creatClassList("show-word-limit"));

                var length = $el.val().length;
                var $span = $("<span>" + [length, maxlength].join("/") + "</span>");

                $span.addClass(creatClassList("word-limit-info"));

                $el.on("input", function () {
                    var length = $el.val().length;
                    $span.text([length, maxlength].join("/"));
                });

                $target.append($span);

                return;
            }
        });
    }

    exports("inputAttr", {
        render: render,
    });
});
