/*
This was supposed to be Takuma's work however we haven't been able to reach him.
He was not updating us on his progress.

We need logout to be implemented for testing proposes and just to finish the whole
application so I (Harley) implemented logout.

Since this is just for testing proposes, it was rushed. It follows clean architecture
but I cut some corners. Moreover, there are no test for this use case.
 */
package use_case.logout;


public interface LogoutOutputBoundary {
    void prepareSuccessView();

}
