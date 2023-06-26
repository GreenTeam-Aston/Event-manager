package aston.greenteam.eventmanager.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private BigDecimal pricc;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    private LocalDate created_at;

    @Column(name = "updated_at")
    private LocalDate updated_at;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_parameters",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"))
    private Set<Parameters> parameters = new HashSet<Parameters>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPricc() {
        return pricc;
    }

    public void setPricc(BigDecimal pricc) {
        this.pricc = pricc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public Set<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameters> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return id == products.id && quantity == products.quantity && Objects.equals(title, products.title) && Objects.equals(description, products.description) && Objects.equals(image, products.image) && Objects.equals(pricc, products.pricc) && Objects.equals(created_at, products.created_at) && Objects.equals(updated_at, products.updated_at) && Objects.equals(parameters, products.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, pricc, quantity, created_at, updated_at, parameters);
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", pricc=" + pricc +
                ", quantity=" + quantity +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", parameters=" + parameters +
                '}';
    }
}
