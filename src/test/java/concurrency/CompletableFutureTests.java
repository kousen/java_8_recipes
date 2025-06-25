package concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CompletableFutureTests {

    @Test
    public void joinAfterCancel() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.cancel(false);
        assertThrows(CancellationException.class, () -> future.join());
    }

    @Test
    public void completeWithGet() {
        CompletableFuture<String> future = new CompletableFuture<>();
        boolean finished = future.complete("I'm done");
        assertTrue(finished);
        try {
            assertEquals("I'm done", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void completeWithJoin() {
        CompletableFuture<String> future = new CompletableFuture<>();
        boolean finished = future.complete("I'm done");
        assertTrue(finished);
        assertEquals("I'm done", future.join());
    }

    @Test
    public void completeExceptionally() {
        assertThrows(ExecutionException.class, () -> parseNumber("abc").get());
    }

    @Test
    public void completeExceptionallyWithCause() {
        try {
            parseNumber("abc").get();
            fail("Should not get here");
        } catch (Exception e) {
            assertEquals(ExecutionException.class, e.getClass());
            assertEquals(NumberFormatException.class, e.getCause().getClass());
        }
    }

    @Test
    public void completeLong() throws Exception {
        assertEquals(42, (long) parseNumber("42").get());
    }

    private CompletableFuture<Long> parseNumber(String arg) {
        CompletableFuture<Long> future = new CompletableFuture<>();
        try {
            future.complete(Long.parseLong(arg));
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        return future;
    }

    @Test @Disabled("problems with CI server")
    public void supplyThenAccept() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        CompletableFuture.supplyAsync(() -> "42")
                .thenApply(Integer::parseInt)
                .thenApply(x -> 2 * x)
                .thenAccept(System.out::println);
        System.out.println("Running...");

        String result = baos.toString();
        System.out.println(result);
        assertTrue(result.contains("84"));
        assertTrue(result.contains("Running..."));
    }

    @Test @Disabled("problems with CI server")
    public void supplyThenAcceptAsyncWithExecutor() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        ExecutorService service = Executors.newFixedThreadPool(4);
        CompletableFuture.supplyAsync(() -> "42", service)
                .thenApply(Integer::parseInt)
                .thenApply(x -> 2 * x)
                .thenAccept(System.out::println);
        System.out.println("Running...");

        String result = baos.toString();
        System.out.println(result);
        assertTrue(result.contains("84"));
        assertTrue(result.contains("Running..."));

    }

    @Test
    public void compose() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCompose(n -> CompletableFuture.supplyAsync(() -> n + y));

        assertEquals(5, (int) completableFuture.get());
    }

    @Test
    public void combine() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCombine(CompletableFuture.supplyAsync(() -> y),
                                (n1, n2) -> n1 + n2);

        assertEquals(5, (int) completableFuture.get());
    }

    private CompletableFuture<Integer> getIntegerCompletableFuture(String num) {
        return CompletableFuture.supplyAsync(() -> Integer.parseInt(num))
                .handle((val, exc) -> val != null ? val : 0);
    }

    @Test
    public void handleWithException() throws Exception {
        String num = "abc";
        CompletableFuture<Integer> value = getIntegerCompletableFuture(num);
        assertEquals(0, (int) value.get());
    }

    @Test
    public void handleWithoutException() throws Exception {
        String num = "42";
        CompletableFuture<Integer> value = getIntegerCompletableFuture(num);
        assertEquals(42, (int) value.get());
    }

}
