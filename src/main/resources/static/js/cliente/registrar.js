class Correo{
    constructor(desCorreo, tipo){
        this._desCorreo = desCorreo;
        this._tipo = tipo;
    }

    get desCorreo(){
        return this._desCorreo;
    }

    get tipo(){
        return this._tipo;
    }

}

class Data{

    constructor(nombre, apellido, listaCorreos){
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaCorreos = listaCorreos;
    }

}

const app = new Vue({
    el : '#app',
    data : {
        nombre : '',
        apellido : '',
        correo : '',
        tipoCorreo : 1,
        listaCorreos : []
    },
    methods : {
        obtenerListaCorreos : function () {
            return this.listaCorreos;
        },
        agregarCorreo : function (e) {
            e.preventDefault();
            let result = new RegExp("[a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,15})").exec(this.correo);

            if (!result) {
                swal(
                    'Correo',
                    'Ingrese un correo válido',
                    'warning'
                );
                return;
            }

            let exists = false;

            for(let i = 0; i < this.listaCorreos.length && !exists ; i++){
                if (this.listaCorreos[i].desCorreo === this.correo){
                    swal(
                        'Correo',
                        'Correo ya existente',
                        'warning'
                    );
                    exists = true;
                }
            }

            if (!exists){
                this.listaCorreos.push(new Correo(this.correo, this.tipoCorreo));
                this.correo = '';
            }
        },
        eliminarCorreo : function(desCorreo){
            this.listaCorreos = this.listaCorreos.filter(lc => lc.desCorreo !== desCorreo);
        },
        obtenerCorreoAgregados :function () {
            console.log("---------");
            this.listaCorreos.forEach(e => {
                console.log("Correo : " + e.desCorreo + "  Tipo : " + e.tipo);
            });
        },
        sendForm :function () {

            if (this.nombre.trim() === '' || this.apellido.trim() === ''){
                swal(
                    'Campos Vacios',
                    'Existes campos vacios',
                    'error'
                );
                return;
            }

            let data = new Data(this.nombre, this.apellido, this.listaCorreos);

            console.log(JSON.parse(JSON.stringify(data)));

            axios.post('/cliente/registrar', data)
                .then(function (response) {
                    if (response.data === "ok"){
                        location.href="/";
                    } else {
                        swal(
                            'Correo',
                            'Ocurrio un problema al registrar el Cliente',
                            'error'
                        );
                    }

                })
                .catch(function (error) {
                    swal(
                        'Correo',
                        'Ocurrio un problema al registrar el Cliente',
                        'error'
                    );
                });
        }
    }
});

