/**
 * 
 */
//script validación campos del registro -->
function validateRegister() {
 mensaje='';
 flag_ok=true;

 var x = document.forms["myForm"]["Nombre"].value;
 if (x == "") {
 mensaje = mensaje + "Nombre de usuario no puede ser nulo \n";
 flag_ok = false;
 }
 var x = document.forms["myForm"]["password"].value;
 if (x == "") {
 mensaje = mensaje + "La contraseña no debe ser nula \n";
 flag_ok = false;
 }
  var x = document.forms["myForm"]["confirmpassword"].value;
 if (x == "") {
 mensaje = mensaje + "La cofirmación de la contraseña no debe ser nula \n";
 flag_ok = false;
 }

 
    var clave1 = document.myForm.password.value
    var clave2 = document.myForm.confirmpassword.value
 if(clave1!=clave2){
    mensaje = mensaje + "Las contraseñas no coinciden \n";
     flag_ok=false;
 }
 
 if (flag_ok == false) {
 alert(mensaje);
 return false;
 } else
 return true;
}