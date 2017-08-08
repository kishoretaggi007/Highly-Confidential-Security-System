function SetChecked(ch) {
	if (document.f.ch[0].checked) {
		for (i = 0; i < document.f.ch.length; i++) {
			if (document.f.ch[i].name == ch) {
				document.f.ch[i].checked = true;
			}
		}
	} else {
		for (i = 0; i < document.f.ch.length; i++) {
			if (document.f.ch[i].name == ch) {
				document.f.ch[i].checked = false;
			}
		}
	}
}

function check1() {
var flag = false;
  for( i=1 ; i<document.f.ch.length ; i++) {
		if (document.f.ch[i].checked) {
		    document.f.ch[0].checked=true;
		    flag = true;
		    break;
		}
	}
  if(!flag){
	   document.f.ch[0].checked=false; 
	}
}
function validate()
{
   var temp = document.register;
   if(temp.fname.value=="" || temp.lname.value=="" || temp.bdate.value=="" || temp.loginname.value=="" || temp.password.value=="" || temp.sanswer.value=="")
   {
       alert("All Fields are manditory");
       return false;
   }
   if(temp.ch.checked && temp.ownquest.value=="")
   {
       alert("All Fields are manditory");
       return false;
   }
   return true;
}
function check()
{
    var form = document.register;
    if(!form.ch.checked){
          form.ownquest.disabled=true;
          form.securityQestion.disabled=false;
    }
    else{
          form.ownquest.disabled=false;
          form.securityQestion.disabled=true;
    }
    /*if(!form.home.checked){
          form.hno.disabled=true;
          form.street.disabled=true;
           form.city.disabled=true;
            form.state.disabled=true;
             form.country.disabled=true;
              form.pincode.disabled=true;
              form.phoneno.disabled=true;
    }
    else{
          form.hno.disabled=false;
          form.street.disabled=false;
           form.city.disabled=false;
            form.state.disabled=false;
             form.country.disabled=false;
              form.pincode.disabled=false;
              form.phoneno.disabled=false;
         
    }
    if(!form.office.checked){
          form.ohno.disabled=true;
          form.ostreet.disabled=true;
           form.ocity.disabled=true;
            form.ostate.disabled=true;
             form.ocountry.disabled=true;
              form.opincode.disabled=true;
              form.ophoneno.disabled=true;
    }
    else{
          form.ohno.disabled=false;
          form.ostreet.disabled=false;
           form.ocity.disabled=false;
            form.ostate.disabled=false;
             form.ocountry.disabled=false;
              form.opincode.disabled=false;
              form.ophoneno.disabled=false;
         
    }
    if(!form.contact.checked){
          form.chno.disabled=true;
          form.cstreet.disabled=true;
           form.ccity.disabled=true;
            form.cstate.disabled=true;
             form.ccountry.disabled=true;
              form.cpincode.disabled=true;
              form.cphoneno.disabled=true;
    }
    else{
          form.chno.disabled=false;
          form.cstreet.disabled=false;
           form.ccity.disabled=false;
            form.cstate.disabled=false;
             form.ccountry.disabled=false;
              form.cpincode.disabled=false;
               form.cphoneno.disabled=false;
          
    }
*/    
}

