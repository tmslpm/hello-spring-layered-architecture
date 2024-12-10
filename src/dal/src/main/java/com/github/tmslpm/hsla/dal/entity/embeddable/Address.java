package com.github.tmslpm.hsla.dal.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre><code>// note:
 * public class MyEntity extends BaseEntity {
 *  {@literal @Embedded @Getter}
 *   private Address location;
 * }
 * </code></pre>
 */
@Embeddable
@SuppressWarnings("unused")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public final class Address {
    @Column(name = "address_street", nullable = false)
    private String street;
    @Column(name = "address_number", nullable = false)
    private String number;
    @Column(name = "address_locality", nullable = false)
    private String locality;
    @Column(name = "address_postal_code", nullable = false)
    private String code;
    @Column(name = "address_country", nullable = false)
    private String country;
}
