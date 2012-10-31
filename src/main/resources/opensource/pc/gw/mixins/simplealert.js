(function ($) {

    T5.extendInitializers(function () {
        function init(spec) {
            $('#' + spec.elementId).click(function(event){
                alert(spec.message)
            })
        }

        return {
            installSimpleAlert:init
        };
    });
})(jQuery);