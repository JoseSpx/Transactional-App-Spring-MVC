-- OBLIGATORIO
insert into tipocorreo(estado, nombre) values ('0', 'Personal');
insert into tipocorreo(estado, nombre) values ('0', 'Oficina');

-- OPCIONAL
insert into producto(codigo, descripcion, nombre, precio, cantidad) values ('1000', 'gaseosa negra', 'Coca Cola 2L', '5', 200)
insert into producto(codigo, descripcion, nombre, precio, cantidad) values ('1001', 'snack', 'Papas Lays', '3', 300)
insert into producto(codigo, descripcion, nombre, precio, cantidad) values ('1002', 'gaseosa de coco', 'Galleta Rellenita', '1', 400)
insert into producto(codigo, descripcion, nombre, precio, cantidad) values ('1003', 'snack de queso', 'Cuates', '2', 500)

insert into cliente(nombre, apellido, estado) values('Luis Enrique', 'Perez Tello', '1')
insert into cliente(nombre, apellido, estado) values('Pedro Martin', 'Quispe Larco', '1')
insert into cliente(nombre, apellido, estado) values('Jaime Carlos', 'Mantilla Rodriguez', '1')
insert into cliente(nombre, apellido, estado) values('Victor Freddy', 'Portales Pereda', '1')

insert into pedido(monto, estado, fechapedido, cliente_id) values('34.50', '1', getDate(), '1')
-- insert into pedido(monto, estado, fechapedido, cliente_id) values('50', '1',getDate(), '1')
-- insert into pedido(monto, estado, fechapedido, cliente_id) values('60', '1', getDate(), '1')

insert into pedidoproducto(cantidad, precio, pedido_id, producto_id) values(3, 10, 1, 1)
insert into pedidoproducto(cantidad, precio, pedido_id, producto_id) values(4, 20, 1, 2)
insert into pedidoproducto(cantidad, precio, pedido_id, producto_id) values(5, 30, 1, 3)

