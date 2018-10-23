
class Producto{

    constructor(codigo, nombre, precio, descripcion, cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

}


const app = new Vue({
   el : '#app',
   data : {
       codigo : '',
       nombre : '',
       precio : 0,
       descripcion : '',
       cantidad : 0,
   },
   methods : {
       sendForm : function () {
           if (this.codigo.trim() === '' || this.nombre.trim() === '' || this.precio.trim() === ''){
               swal(
                   'Producto',
                   'Existen campos vacios',
                   'error'
               );
               return;
           }

           let producto = new Producto(this.codigo, this.nombre, this.precio, this.descripcion, this.cantidad);

           axios.post('/producto/registrar', producto)
               .then(function (response) {
                   console.log(response);
                   console.log(response.data);
                   if (response.data === "ok"){
                       location.href="/producto/lista";
                   } else {
                       swal(
                           'Correo',
                           'Codigo ya existe',
                           'error'
                       );
                   }

               })
               .catch(function (error) {
                   console.log(error);
                   swal(
                       'Correo',
                       'Ocurrio un problema al registrar el Producto catch',
                       'error'
                   );
               });
       }
   } 
});