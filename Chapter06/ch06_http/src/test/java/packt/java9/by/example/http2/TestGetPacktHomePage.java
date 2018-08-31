package packt.java9.by.example.http2;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class TestGetPacktHomePage {

    @Test
    public void getsTheContent() throws InterruptedException, IOException, URISyntaxException {
        GetPacktHomePage getPacktHomePage = new GetPacktHomePage();
        System.out.println((getPacktHomePage.getHttp1_1()));
    }

    @Test
    public void getHttpAsync() throws InterruptedException, IOException, URISyntaxException, ExecutionException {
        GetPacktHomePage getPacktHomePage = new GetPacktHomePage();
        System.out.println((getPacktHomePage.getHttpAsync()));
    }
    @Test
    public void g() throws InterruptedException, IOException, URISyntaxException, ExecutionException {
        GetPacktHomePage getPacktHomePage = new GetPacktHomePage();
        System.out.println((getPacktHomePage.getHttp2()));
    }
}
