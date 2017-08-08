/**
 * DHTML phone number validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */

// Declaring required variables
var digits = "0123456789";
// non-digit characters which are allowed in phone numbers
var phoneNumberDelimiters = "()- ";
// characters which are allowed in international phone numbers
// (a leading + is OK)
var validWorldPhoneChars = phoneNumberDelimiters + "+";
// Minimum no of digits in an international phone no.
var minDigitsInIPhoneNumber = 10;

function isNumber(s1) {

	var s = s1.value;
	var i;
	var name = s1.name;
	for (i = 0; i < s.length; i++) {
		// Check that current character is number.
		var c = s.charAt(i);
		if (((c < "0") || (c > "9"))) {
			alert(name + " -------------------Please Enter a Number");
			s1.focus();
			return false;
		}

	}
	// All characters are numbers.
	return true;
}

function isInteger(s) {

	var i;
	for (i = 0; i < s.length; i++) {
		// Check that current character is number.
		var c = s.charAt(i);
		if (((c < "0") || (c > "9")))
			return false;
	}
	// All characters are numbers.
	return true;
}

function stripCharsInBag(s, bag) {
	var i;
	var returnString = "";
	// Search through string's characters one by one.
	// If character is not in bag, append to returnString.
	for (i = 0; i < s.length; i++) {
		// Check that current character isn't whitespace.
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1)
			returnString += c;
	}
	return returnString;
}

function checkInternationalPhone(strPhone) {
	s = stripCharsInBag(strPhone, validWorldPhoneChars);
	return (isInteger(s) && s.length >= minDigitsInIPhoneNumber);
}

function ValidateForm() {
	var Phone = document.register.phoneNo

	if ((Phone.value == null) || (Phone.value == "")) {
		alert("Please Enter your Phone Number")
		Phone.focus()
		return false
	}
	if (checkInternationalPhone(Phone.value) == false) {
		alert("Please Enter a Valid Phone Number")
		Phone.value = ""
		Phone.focus()
		return false
	}
	return true
}
function showStatus() {
	var form = document.register;
	var selectWidget = document.forms.register.elements["addressType"];

	var myValue = selectWidget.options[selectWidget.selectedIndex].value;
	if (myValue == "home") {
		document.register.homeaddresstype.value += myValue
		document.register.homehouseno.value += document.register.houseNo.value
		document.register.homestreet.value += document.register.street.value
		document.register.homephonetype.value += document.register.phoneType.value
		document.register.homecountry.value += document.register.country.value
		document.register.homepin.value += document.register.pin.value
		document.register.homestate.value += document.register.state.value
		document.register.homephoneno.value += document.register.phoneNo.value
		document.register.homecity.value += document.register.city.value

	} else if (myValue == "office") {
		document.register.officeaddresstype.value += myValue
		document.register.officehouseno.value += document.register.houseNo.value
		document.register.officestreet.value += document.register.street.value
		document.register.officephonetype.value += document.register.phoneType.value
		document.register.officecountry.value += document.register.country.value
		document.register.officepin.value += document.register.pin.value
		document.register.officestate.value += document.register.state.value
		document.register.officephoneno.value += document.register.phoneNo.value
		document.register.officecity.value += document.register.city.value

	} else if (myValue == "personal") {
		document.register.personaladdresstype.value += myValue
		document.register.personalhouseno.value += document.register.houseNo.value
		document.register.personalstreet.value += document.register.street.value
		document.register.personalphonetype.value += document.register.phoneType.value
		document.register.personalcountry.value += document.register.country.value
		document.register.personalpin.value += document.register.pin.value
		document.register.personalstate.value += document.register.state.value
		document.register.personalphoneno.value += document.register.phoneNo.value
		document.register.personalcity.value += document.register.city.value

	}
	return true;
}
function checkconformpassword() {
	var password = register.password.value;
	var conformpassword = register.conformpassword.value

	if (password != conformpassword) {
		alert("ConformPassword does not matche with Password");
		return false;
	}
	return true;
}

function cleartext() {

	document.register.houseNo.value = "";
	document.register.state.value = "";

	document.register.street.value = "";
	document.register.country.value = "";
	document.register.pin.value = "";
	document.register.phoneNo.value = "";
	document.register.city.value = "";

	return " ";
}
function Encrypt(theText) {
	output = new String;
	Temp = new Array();
	Temp2 = new Array();
	TextSize = theText.length;
	for (i = 0; i < TextSize; i++) {
		rnd = Math.round(Math.random() * 122) + 68;
		Temp[i] = theText.charCodeAt(i) + rnd;
		Temp2[i] = rnd;
	}
	for (i = 0; i < TextSize; i++) {
		output += String.fromCharCode(Temp[i], Temp2[i]);
	}
	return output;
}

function strValidate(string) {
	var string1 = string.value

	var testName = /^([a-z]|[A-Z]| )*$/;

	if (!testName.test(string1)) {
		alert(string.name + "Enter only string values");
		string.focus();
		return false;
	}

	return true;
}
function abc() {

	var form = document.register

	form.city.focus();
	return false;
	return true;

}
function abc1() {

	var form = document.register
	form.squest.focus();
	return false;
	return true;
}

function showStatus1() {
	var form = document.register;
	var selectWidget = document.forms.register.elements["addressType"];

	var myValue = selectWidget.options[selectWidget.selectedIndex].value;
	if (myValue == "home") {
		document.register.homeaddresstype.value = myValue
		document.register.homehouseno.value = document.register.houseNo.value
		document.register.homestreet.value = document.register.street.value
		//document.register.homephonetype.value=document.register.phoneType.value  
		document.register.homecountry.value = document.register.country.value
		document.register.homepin.value = document.register.pin.value
		document.register.homestate.value = document.register.state.value
		document.register.homephoneno.value = document.register.phoneNo.value
		document.register.homecity.value = document.register.city.value
		//document.register.homefax.value=document.register.fax.value

	} else if (myValue == "office") {
		document.register.officeaddresstype.value = myValue
		document.register.officehouseno.value = document.register.houseNo.value
		document.register.officestreet.value = document.register.street.value
		//  document.register.officephonetype.value=document.register.phoneType.value  
		document.register.officecountry.value = document.register.country.value
		document.register.officepin.value = document.register.pin.value
		document.register.officestate.value = document.register.state.value
		document.register.officephoneno.value = document.register.phoneNo.value
		document.register.officecity.value = document.register.city.value
		//document.register.officefax.value=document.register.fax.value

	} else if (myValue == "personal") {
		document.register.personaladdresstype.value = myValue
		document.register.personalhouseno.value = document.register.houseNo.value
		document.register.personalstreet.value = document.register.street.value
		//document.register.personalphonetype.value=document.register.phoneType.value  
		document.register.personalcountry.value = document.register.country.value
		document.register.personalpin.value = document.register.pin.value
		document.register.personalstate.value = document.register.state.value
		document.register.personalphoneno.value = document.register.phoneNo.value
		document.register.personalcity.value = document.register.city.value
		//document.register.personalfax.value=document.register.fax.value

	}
	return true;
}
