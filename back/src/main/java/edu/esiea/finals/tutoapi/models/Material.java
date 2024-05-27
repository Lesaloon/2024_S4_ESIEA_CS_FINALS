package edu.esiea.finals.tutoapi.models;

import javax.persistence.*;

@Entity
@Table(name = "materials")

@NamedQueries({ @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
		@NamedQuery(name = "Material.findById", query = "SELECT m FROM Material m WHERE m.id = :id") })
public class Material {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int id;
    
    @Column(name = "material_name")
    private String name;
    
    @Lob
    @Column(name = "material_description")
    private String description;
    
    @Lob
    @Column(name = "material_photo")
    private byte[] photo;
    
    @Column(name = "material_quantity")
    private Integer quantity;
    
    @Column(name = "material_unit")
    private String unit;
    /**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the photo
	 */
	public final byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public final void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the quantity
	 */
	public final Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public final void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unit
	 */
	public final String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public final void setUnit(String unit) {
		this.unit = unit;
	}
}
