package net.datafaker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SipTest extends AbstractFakerTest {

    @Test
    public void method_returnUpperCaseWithMinimum3Chars() {
        assertThat(faker.sip().method()).matches("^[A-Z]{3,}$");
    }

    @Test
    public void contentType_returnLowerCaseTwoWordsSepereatedBySlashMinimum3And4Chars() {
        assertThat(faker.sip().contentType()).matches("^[a-z]{4,}[/]{1,}[a-z0-9-]{3,}$");
    }

    @Test
    public void messagingPort_return4DigitIntBetween1000And9999() {
        assertThat(faker.sip().messagingPort()).isBetween(1000, 10000);
    }

    @Test
    public void rtpPort_returnPositiveEvenInt() {
        int sut = faker.sip().rtpPort();
        assertThat(sut).isGreaterThanOrEqualTo(2);
        assertThat(sut % 2).isEqualTo(0);
    }

    @Test
    public void provisionalResponseCode_return3DigitIntBetween100And199() {
        assertThat(faker.sip().provisionalResponseCode()).isBetween(100, 200);
    }

    @Test
    public void successResponse_Codereturn3DigitIntBetween200And299() {
        assertThat(faker.sip().successResponseCode()).isBetween(200, 300);
    }

    @Test
    public void redirectResponseCode_Codereturn3DigitIntBetween300And399() {
        assertThat(faker.sip().redirectResponseCode()).isBetween(300, 400);
    }

    @Test
    public void clientErrorResponseCode_Codereturn3DigitIntBetween400And499() {
        assertThat(faker.sip().clientErrorResponseCode()).isBetween(400, 500);
    }

    @Test
    public void serverErrorResponseCode_Codereturn3DigitIntBetween500And599() {
        assertThat(faker.sip().serverErrorResponseCode()).isBetween(500, 600);
    }

    @Test
    public void globalErrorResponseCode_Codereturn3DigitIntBetween600And699() {
        assertThat(faker.sip().globalErrorResponseCode()).isBetween(600, 700);
    }

    @Test
    public void provisionalResponsePhrase_returnAnyNonDigitString() {
        assertThat(faker.sip().provisionalResponsePhrase()).matches("\\D+");
    }

    @Test
    public void successResponsePhrase_returnAnyNonDigitString() {
        assertThat(faker.sip().successResponsePhrase()).matches("\\D+");
    }

    @Test
    public void redirectResponsePhrase_returnAnyNonDigitString() {
        assertThat(faker.sip().redirectResponsePhrase()).matches("\\D+");
    }

    @Test
    public void clientErrorResponsePhrase_returnAnyNonDigitString() {
        assertThat(faker.sip().clientErrorResponsePhrase()).matches("\\D+");
    }

    @Test
    public void serverErrorResponsePhrase_returnAnyNonDigitString() {
        assertThat(faker.sip().serverErrorResponsePhrase()).matches("\\D+");
    }

    @Test
    public void globalErrorResponsePhrase_returnAnyNonDigitString() {
        assertThat(faker.sip().globalErrorResponsePhrase()).matches("\\D+");
    }

    @Test
    public void bodyString_returnAValidSdpBodyString() {
        String[] sut = faker.sip().bodyString().split("\n");

        assertThat(sut.length).isEqualTo(7);

        assertThat(sut[0]).isEqualTo("v=0");

        String[] secondLine = sut[1].split(" ");
        assertThat(secondLine[0]).startsWith("o=");
        assertThat(secondLine[1]).matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
        assertThat(secondLine[secondLine.length - 1]).matches("[a-z]+\\.\\w{2,4}");

        assertThat(sut[2]).isEqualTo("s=-");

        String[] fourthLine = sut[3].split(" ");
        assertThat(fourthLine[0]).isEqualTo("c=IN");
        assertThat(fourthLine[fourthLine.length - 1]).matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}$");

        assertThat(sut[4]).isEqualTo("t=0 0");

        String[] sixthLine = sut[5].split(" ");
        assertThat(sixthLine[0]).isEqualTo("m=audio");
        assertThat(Integer.parseInt(sixthLine[1])).isGreaterThanOrEqualTo(2);
        assertThat(Integer.parseInt(sixthLine[1]) % 2).isEqualTo(0);

        assertThat(sut[6]).isEqualTo("a=rtpmap:0 PCMU/8000");
    }

    @Test
    public void bodyBytes_isNotNull() {
        byte[] sut = faker.sip().bodyBytes();

        assertThat(sut.length).isNotNull();
    }

    @Test
    public void nameAddress_returnValidNameAddressString() {
        String[] sut = faker.sip().nameAddress().split("@");

        assertThat(sut[0].split(":")[1]).matches("\\w+");
        assertThat(sut[1].split(":")[0]).matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}$");
    }
}
