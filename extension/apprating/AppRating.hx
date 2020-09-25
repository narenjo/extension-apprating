package extension.apprating;

import openfl.net.URLRequest;
import openfl.Lib;

#if (openfl < "4.0.0")
import openfl.utils.JNI;
#else
import lime.system.JNI;
#end

class AppRating {

	private static inline var IOS_LINK:String = 'itms://itunes.apple.com/us/app/apple-store/id';

	public static function redirectToStore(appId:String):Void {
		#if ios
		Lib.getURL(new URLRequest(IOS_LINK + appId));
		#end
		#if android
		JNI.createStaticMethod("apprating/AppRating", "launchReviewFlow", "()V");
		#end
	}
}
