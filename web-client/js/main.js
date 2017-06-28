var itemList = [];
var userList = [];

function onLoad() {
	
    userList.push(new User("Default User", "user", "pass", "user@example.com"));
    userList.push(new User("Default User2", "user2", "pass2", "user2@example.com"));
    userList.push(new User("Default User3", "user3", "pass3", "user3@example.com"));
    displayUsers();
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