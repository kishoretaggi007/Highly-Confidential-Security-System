function validateFields()
{
	var errMsg = "";

	var myform = document.forms[0].elements;

	for( i=0; i<myform.length-1; i++ )
	{
		if( myform[i].value == "" || myform[i].value == "none" )
			if( myform[i].emsg == null )
				errMsg += myform[i].name + " is Required\n";
			else
				errMsg += myform[i].emsg + " \n";
	}
	if( errMsg == "" )
		return true;
	alert( errMsg );
	return false;
}
