package celiocausjunior.DSCommerce.models.dtos;

import java.time.Instant;

import celiocausjunior.DSCommerce.models.PaymentModel;

public class PaymentDTO {
    
    private Long id;
    private Instant moment;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(PaymentModel entity){
        this.id = entity.getId();
        this.moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }


}
