package com.techment.OtrsSystem.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_features")
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "feature_name")
    private String featureName;

    @Column(name = "feature_description")
    private String featureDescription;

    //default constructor
    protected Features() {}

    //parametrized constructor


    public Features(String featureName, String featureDescription) {
        this.featureName = featureName;
        this.featureDescription = featureDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }
}
