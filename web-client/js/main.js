var itemList = [];
var userList = [];

function onLoad() {
	
    userList.push(new User("Default User", "user", "pass", "user@example.com"));
    userList.push(new User("Default User2", "user2", "pass2", "user2@example.com"));

    itemList.push(new Item("LOST", "Mittens", "A cute kitty.", "Atlanta", "MISC", 1000000, userList[0]));
    itemList.push(new Item("FOUND", "Sparky", "A smoll pupper.", "Atlanta", "MISC", 1000000, userList[1]));
    
    displayUsers();
    displayItems();
}

function User(name, loginID, password, email) {
	
	this.name = name;
    this.loginID = loginID;
    this.password = password;
    this.email = email;
    this.isLocked = false;
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

function Item(itemType, name, description,
	location, catergory, reward, creator) {
	
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