var ufomg =  {
  init: function(){
    ufomg.presentation();
    ufomg.events();
  },
  config: {
    feedData: [],
    activeUser: "",
    userRoute: "user-",
    sightingRoute: "sighting-",
    crudActions: ["create", "read", "update", "delete"],
    pollingFunction: undefined,
    geoKey: "AIzaSyBbfNbCqRj5EvUHiGcKahKFu49CYetrxaE",
    geoRoute: "https://maps.googleapis.com/maps/api/js?key=",
    geocoder: undefined,
    geocoderResult: undefined,
  },
  presentation: function(){

  },
  events: function(){
    $('.submit input').on('click', ufomg.login);
    $('.add-new input').on('click', );
  },
  getTemplate: function(templateName){
    return templates[templateName];
  },
  constructTemplate: function(templateName, str){
    var tmpl = _.templates(ufomg.getTemplate(templateName));
    return tmpl(str);
  },
  buildFeedRow: function(templateName, data, target){
    var output = ufomg.constructTemplate(templateName, data);
    $(target).prepend(output);
  },
  buildFeed: function(array, target, templateName){
    array.forEach(function(el){
      ufomg.buildFeedRow(templateName, el, target);
    });
  },
  login: function(){
    var login = getLogin();

  },
  getLogin: function(){
    var username = $('.username input').val();
    var password = $('.password input').val();
    $('.login input[type="text"]').val('');
    return [username, password];
  },
  logout: function(){

  },
  addUser: function(data){
    $.ajax({
      url: ufomg.userRoute + ufomg.crudActions[0],
      method: "POST",
      success: function(result){

      },
      error: function(error){
        console.log("Add User", error);
      }
    });
  },
  getUser: function(data) {
    $.ajax({
      url: ufomg.userRoute + ufomg.crudActions[1],
      method: "GET",
      success: function(result){

      },
      error: function(error){
        console.log("Get User", error);
      }
    });
  },
  getFeed: function(data) {
    $.ajax({
      url: ufomg.sightingRoute + ufomg.crudActions[1],
      method: "GET",
      success: function(result){
        if(result.length !== ufomg.config.feedData.length) {
          ufomg.config.feedData = result;
        }
      },
      error: function(error){
        console.log("Get Feed", error);
      }
    });
  },
  editFeed: function(data) {
    $.ajax({
      url: ufomg.sightingRoute + ufomg.crudActions[2],
      method: "PUT",
      success: function(result){

      },
      error: function(error){
        console.log("Edit Feed", error);
      }
    });
  },
  addFeed: function(data) {
    $.ajax({
      url: ufomg.sightingRoute + ufomg.crudActions[0],
      method: "POST",
      success: function(result){

      },
      error: function(error){
        console.log("Add Feed", error);
      }
    });
  },
  deleteFeed: function(data) {
    $.ajax({
      url: ufomg.sightingRoute + ufomg.crudActions[3],
      method: "DELETE",
      success: function(result){

      },
      error: function(error){
        console.log("Delete Feed", error);
      }
    });
  },
  checkDataUpdates: function(){
    ufomg.pollData(ufomg.getFeed, 1000, true);
  },
  pollData: function(callback, pollRate, flag){
    if(flag){
      ufomg.config.pollingFunction = setInterval(callback, pollRate);
    } else if(!flag) {
      clearInterval(ufomg.config.pollingFunction)
    }
  },
  initializeGeocoder: function(){
    ufomg.config.geocoder = new google.maps.Geocoder();
  },
  codeAddress: function(address){
    ufomg.config.geocoder.geocode({ 'address': address }, function(results, status){
      if(status === google.maps.GeocoderStatus.OK) {
        ufomg.config.geocoderResult = results;
      } else {
        console.log("Geocoder error", status);
      }
    });
  },
  decodeAddress: function(coords){
  }
}