function highlightWords(keywords, element) {
    if(keywords) {
        var textNodes;
        keywords = keywords.replace(/\W/g, '');
        var str = keywords.split(" ");
        $(str).each(function() {
            var term = this;
            var textNodes = $(element).contents().filter(function() { return this.nodeType === 3 });
            textNodes.each(function() {
                var content = $(this).text();
                var regex = new RegExp(term, "gi");
                content = content.replace(regex, '<span class="highlight">' + term + '</span>');
                $(this).replaceWith(content);
            });
        });
    }
}