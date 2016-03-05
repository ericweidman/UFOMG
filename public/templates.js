var templates = {
  feedItem: [
    "<div class='feedItem' data-stellar-background-ratio='0.5'>",
      "<div class='feedInfoContainer'>",
        "<span class='feedUser'><%= username %></span>",
        "<span class='feedTime'><%= timestamp %></span>",
        "<span class='feedLocation'><%= location %></span>",
      "</div>",
      "<span class='feedImg'><img src='<%= img %>'/></span>",
      "<p><%= text %></p>",
    "</div>"
  ].join(""),
}
