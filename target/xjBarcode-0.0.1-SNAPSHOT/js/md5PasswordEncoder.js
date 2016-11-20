function encode(user, password)
{
  var encPassword = "";
//  if (password != "") 
      encPassword = b64_md5(user + password );
  
  return encPassword;
}

function encodePassword(user, password, encPassword)
{
  encPassword.value = encode(user.value, password.value);
  password.value = "";
}
