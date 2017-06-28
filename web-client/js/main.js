var itemList = [];
var userList = [];

function onLoad() {
	itemList.push(new User("Default User", "user", "pass", "user@example.com"));
}

function User(name, loginID, password, email) {
	
	this.name = name;
    this.loginID = loginID;
    this.password = password;
    this.email = email;
    this.isLocked = false;
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