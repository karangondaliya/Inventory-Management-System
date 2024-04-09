package com.ims.dto;

public class StockDTO {
	
	private int id;
	
    private ProductDTO productDTO;
	
	private long quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StockDTO [id=" + id + ", productDTO=" + productDTO + ", quantity=" + quantity + "]";
	}

	
}
