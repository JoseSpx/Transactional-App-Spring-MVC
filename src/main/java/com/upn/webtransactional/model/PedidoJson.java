package com.upn.webtransactional.model;

import java.util.List;

public class PedidoJson {

    private int clienteid;
    private List<PedidoData> productoPedidoList;

    public PedidoJson() {}

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    public List<PedidoData> getProductoPedidoList() {
        return productoPedidoList;
    }

    public void setProductoPedidoList(List<PedidoData> productoPedidoList) {
        this.productoPedidoList = productoPedidoList;
    }
}
