


var itemList = [];
var userList = [];
localStorage.setItem("itemList", JSON.stringify(itemList));
localStorage.setItem("userList", JSON.stringify(userList));

//MODEL----------------------------------------------------------------------
function onLoad() {
    // if userList.length == 0 && itemList.length == 0
	addUser(new User("Default User", "user", "pass", "user@example.com"));
    // userList.push(new User("Default User", "user", "pass", "user@example.com"));
    // userList.push(new User("Default User2", "user2", "pass2", "user2@example.com"));

    addItem(new Item("LOST", "Mittens", "A cute kitty.", "Atlanta", "MISC", 1000000, userList[0]));
    // itemList.push(new Item("FOUND", "Sparky", "A smoll pupper.", "Atlanta", "MISC", 1000000, userList[1]));
    
    displayUsers();
    displayItems();
}

function addUser(user) {
    var JSONul = localStorage.getItem("userList");
    userList = JSON.parse(JSONul);
    userList.push(user);
    JSONul = JSON.stringify(userList);
    localStorage.setItem("userList", JSONul);
}

function addItem(item) {
    var JSONil = localStorage.getItem("itemList");
    itemList = JSON.parse(JSONil);
    itemList.push(item);
    JSONil = JSON.stringify(itemList);
    localStorage.setItem("itemList", JSONil);
}

function displayUsers() {

    content = document.getElementById("content");
    for (var i = 0; i < userList.length; i++) {
        content.innerHTML += "<p>" + userList[i].name + "</p>";
    }
}

function displayItems() {

    content = document.getElementById("content");
    for (var i = 0; i < itemList.length; i++) {
        content.innerHTML += "<p>" + itemList[i].name + ": " + itemList[i].description + "</p>";
    }
}

//USER-----------------------------------------------------------------

function User(name, loginID, password, email) {
	
	this.name = name;
    this.loginID = loginID;
    this.password = password;
    this.email = email;
    this.isLocked = false;
}

//ITEM-------------------------------------------------------------------

function Item(itemType, name, description,
	location, category, reward, creator) {
	
	this.itemType = itemType;
    this.name = name;
    this.description = description;
    this.location = location;
    this.category = category;
    this.reward = reward;
    this.creator = creator;
    this.open = true;
    this.date = new Date();
}

//LOGIN/REGISTER------------------------------------------------------

function register() {
    var form = document.getElementById("registerForm");
    var name = form.elements[0].value;
    var id = form.elements[1].value;
    var pass = form.elements[2].value;
    addUser(new User(name, id, pass, ''));

}