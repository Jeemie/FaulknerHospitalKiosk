package Map;

/**
 * Created by trietpham on 4/20/16.
 */
public enum Icons {


    Bathroom("bathroom.png"),
    BloodDraw("bloodDraw.png"),
    Conference("conference.png"),
    Elevator("elevator.png"),
    Stair("stair.png"),
    Emergency("emergency.png"),
    Kiosk("kiosk.png"),
    Lobby("lobby.png"),
    DoctorService("doctor.png"),
    LungService("lung.png"),
    MedicalServie("medical.png"),
    MentalHealth("mental.png"),
    Radiology("radiology.png"),
    Surgery("surgery.png"),
    VolunteerService("volunteer.png"),
    Audiotorium("audi.png"),
    Cadeteria("cafe.png"),
    Chapel("chapel.png"),
    Giftshop("gift.png"),
    NurseService("nurse.png");



        private String imagePath;

        private Icons(String imagePath) {
            this.imagePath = imagePath;
        }

    }