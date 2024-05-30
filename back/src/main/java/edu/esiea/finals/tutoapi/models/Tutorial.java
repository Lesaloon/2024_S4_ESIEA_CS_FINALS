package edu.esiea.finals.tutoapi.models;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;

import edu.esiea.finals.tutoapi.models.enums.DifficultyLevel;
import edu.esiea.finals.tutoapi.models.enums.Type;

import java.util.List;

@Entity
@Table(name = "tutorial")
@NamedQueries({ @NamedQuery(name = "Tutorial.findAll", query = "SELECT t FROM Tutorial t"),
		@NamedQuery(name = "Tutorial.findById", query = "SELECT t FROM Tutorial t WHERE t.id = :id") })
public class Tutorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutorial_id")
    private int id;
    
    @Column(name = "tutorial_title")
    private String title;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tutorial_type")
    private Type type;
    
    @Lob
    @Column(name = "tutorial_description")
    private String description;
    
    @Column(name = "tools")
    private String tools;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tutorial_id")
    private List<Material> materials;
    
    @Lob
    @Column(name = "tutorial_photo")    
    private String photo;
    
    @Column(name = "tutorial_photo_type")
    private String photoType; // image/jpeg, image/png, etc.

    @Column(name = "time_to_complete")
    private Integer timeToComplete;
    
    @Column(name = "cost")
    private Double cost;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficultyLevel;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tutorial_id")
    private List<Step> steps;

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
	 * @return the type
	 */
	public final Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(Type type) {
		this.type = type;
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
	 * @return the tools
	 */
	public final String getTools() {
		return tools;
	}

	/**
	 * @param tools the tools to set
	 */
	public final void setTools(String tools) {
		this.tools = tools;
	}

	/**
	 * @return the materials
	 */
	public final List<Material> getMaterials() {
		return materials;
	}

	/**
	 * @param materials the materials to set
	 */
	public final void setMaterials(List<Material> materials) {
		this.materials = materials;
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

	/**
	 * @return the photoType
	 */
	public final String getPhotoType() {
		return photoType;
	}

	/**
	 * @param photoType the photoType to set
	 */
	public final void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	/**
	 * @return the timeToComplete
	 */
	public final Integer getTimeToComplete() {
		return timeToComplete;
	}

	/**
	 * @param timeToComplete the timeToComplete to set
	 */
	public final void setTimeToComplete(Integer timeToComplete) {
		this.timeToComplete = timeToComplete;
	}

	/**
	 * @return the cost
	 */
	public final Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public final void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return the difficultyLevel
	 */
	public final DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * @param difficultyLevel the difficultyLevel to set
	 */
	public final void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	/**
	 * @return the steps
	 */
	public final List<Step> getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public final void setSteps(List<Step> steps) {
		this.steps = steps;
	}
   
}
