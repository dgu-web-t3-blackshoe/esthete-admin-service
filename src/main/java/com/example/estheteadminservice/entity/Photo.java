package com.example.estheteadminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "photos")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BINARY(16)", name = "photo_uuid")
    private String photoId;

    private String title;

    private String description;

    private String photoUrl;

    private LocalDateTime createdAt;

    @JoinColumn(name = "photographer_id", foreignKey = @ForeignKey(name = "photo_fk_photographer_id"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Photographer photographer;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoAbusingReport> photoAbusingReports;

    @Builder(builderMethodName = "generatePhoto")
    public Photo(String photoId, String title, String description, String photoUrl, Photographer photographer) {
        this.photoId = photoId;
        this.title = title;
        this.description = description;
        this.photoUrl = photoUrl;
        setPhotographer(photographer);
    }

    public void setPhotographer(Photographer photographer) {
        if (this.photographer != null) {
            this.photographer.getPhotos().remove(this);
        }
        this.photographer = photographer;
        photographer.getPhotos().add(this);
    }
}
