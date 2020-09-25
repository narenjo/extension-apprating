package apprating;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

import org.haxe.extension.Extension;

public class AppRating extends Extension {

    public static void launchReviewFlow() {
        try {
            final ReviewManager manager = ReviewManagerFactory.create(mainContext);
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
                @Override
                public void onComplete(Task<ReviewInfo> task) {
                    if (task.isSuccessful()) {
                        // We can get the ReviewInfo object
                        ReviewInfo reviewInfo = task.getResult();
                        launchReview(manager, reviewInfo);
                    } else {
                        // There was some problem, continue regardless of the result.
                    }
                }
            });
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    private static void launchReview(ReviewManager manager, ReviewInfo reviewInfo){
        Task<Void> flow = manager.launchReviewFlow(mainActivity, reviewInfo);
        flow.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                // The flow has finished. The API does not indicate whether the user
                // reviewed or not, or even whether the review dialog was shown. Thus, no
                // matter the result, we continue our app flow.
            }
        });
    }
}
