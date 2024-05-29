package edu.esiea.finals.tutoapi.models;

import javax.persistence.*;

@Entity
@Table(name = "step")
@NamedQueries({ @NamedQuery(name = "Step.findAll", query = "SELECT s FROM Step s"),
		@NamedQuery(name = "Step.findById", query = "SELECT s FROM Step s WHERE s.id = :id") })
public class Step {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private int id;
    
    @Column(name = "step_title")
    private String title;
    
    @Column(name = "sequence_number")
    @OrderBy
    private Integer sequenceNumber;
    
    @Lob
    @Column(name = "step_description")
    private String description;
    
    @Lob
    @Column(name = "step_photo")
    private String photo;
    
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
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the sequenceNumber
	 */
	public final Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public final void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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
	public final String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public final void setPhoto(String photo) {
		this.photo = photo;
	}

    
}