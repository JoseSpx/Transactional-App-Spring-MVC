class ProductoPedido{
    constructor(id, codigo, nombre, precio, cantidad, monto){
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.monto = monto;
    }
}

class ClientePedido{
    constructor(clienteid, productoPedido){
        this.clienteid = clienteid;
        this.productoPedidoList = productoPedido;
    }
}

const app = new Vue({
    el : '#app',
    created : function(){
        this.getProductos();
    },
    data : {
        list : [],
        productsSelectedList : [],
        cantidad : '1',
        errorcantidad : '',
        montoTotal : 0
    },
    mounted(){
        this.getProductos();
    },
    methods : {
        getProductos : function () {
            axios.get('/producto/todos')
                .then(response => {
                    this.list = response.data
                });
        },
        agregarProducto : function (producto) {
            let reg = new RegExp('^\\d+$').exec(this.cantidad);
            if (reg == null) {
                return;
            }

            if (this.cantidad.trim() === "0"){
                return;
            }

            if (producto.cantidad < this.cantidad){
                return;
            }

            this.productsSelectedList.push(
                new ProductoPedido(
                    producto.id,
                    producto.codigo,
                    producto.nombre,
                    producto.precio,
                    this.cantidad,
                    producto.precio * this.cantidad));

            this.list = this.list.filter(p => p.id !==  producto.id);
            this.cantidad = '1';
            this.montoTotal = 0;

            this.calcularMontoTotal();


        },
        quitarProducto : function (producto) {
            this.productsSelectedList = this.productsSelectedList.filter(p => p.id !==  producto.id);
            this.list.push(producto);
            this.calcularMontoTotal();
        },
        calcularMontoTotal : function(){
            this.montoTotal = 0;
            for(let i = 0; i < this.productsSelectedList.length; i++){
                this.montoTotal += parseFloat(this.productsSelectedList[i].monto);
            }
        },
        sendForm : function () {

            if (this.productsSelectedList.length === 0){
                swal(
                    'Pedido',
                    'Lista de productos vacia',
                    'error'
                );
                return;
            }

            let cliente_id = document.getElementById("idClient").value;
            let cp = new ClientePedido(cliente_id, this.productsSelectedList);

            axios.post('/pedido/registrar', cp)
                .then(function (response) {
                    console.log(response);
                    console.log(response.data);
                    if (response.data === "ok"){
                        location.href="/cliente/" + cliente_id + "/pedido";
                    } else {
                        swal(
                            'Pedido',
                            'Ocurrio un problema al registrar el Pedido',
                            'error'
                        );
                    }

                })
                .catch(function (error) {
                    console.log(error);
                    swal(
                        'Pedido',
                        'Ocurrio un problema al registrar el Pedido',
                        'error'
                    );
                });

        }
    }
});