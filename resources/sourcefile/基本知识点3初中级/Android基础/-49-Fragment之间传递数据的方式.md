####  必知：
####  必会：
### Fragment之间的通讯方式

Fragment之间的通讯方式有很多种, 但是要尽量遵守:` All Fragment-to-Fragment communication is done through the associated Activity. Two Fragments should never communicate directly.` 但是要区分`Fragment`与`Fragment`是否有着相同的宿主`Activity`. 下面具体说说通讯的几种方式(耦合性过高的就不列举了): 

#### 1. `Arugment`传递参数

例如点击`Activity`中某个按钮触发跳转, 需要将`Intent`传递参数到目标`Activity`中`Fragment`:

```java
public class ContentFragment extends Fragment{
	private String mArgument;
	public static final String ARGUMENT = "argument";

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
      	//解析传递的参数
		Bundle bundle = getArguments();
		if (bundle != null)
			mArgument = bundle.getString(ARGUMENT);
	}

	public static ContentFragment newInstance(String argument){
		Bundle bundle = new Bundle();
		bundle.putString(ARGUMENT, argument);
		ContentFragment contentFragment = new ContentFragment();
      	//设置传递的参数
		contentFragment.setArguments(bundle);
		return contentFragment;
	}
}
```

#### 2. 接口回调方式通讯

例如同一个`Activity`中有`HeadlineFragment`与`ArticleFragment`两个`Fragment`展示文章列表和具体内容, 当点击左边列表, 右边将展示具体内容. 

* 列表`Fragment`定义点击条目回调接口

```java
public class HeadlinesFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;
    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
  
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
	
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        mCallback.onArticleSelected(position);
    }
}
```

* 宿主`Activity`实现接口, 并通过接口实现`Fragment`之间的通讯

```java
public static class MainActivity extends Activity
        implements HeadlinesFragment.OnHeadlineSelectedListener{
    //...
    public void onArticleSelected(int position) {
        ArticleFragment articleFrag = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            articleFrag.updateArticleView(position);
        } else {
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
```

#### 3. `startActivityForResult`、`onActivityResult`及`setTargetFragment`等方法

两个`Fragment`在同一个`Activity`中, 点击当前`Fragment`中按钮弹出一个对话框(`DialogFragment`), 在对话框中操作需要返回数据给触发的`Fragment`. 如: 点击`ContentFragment`内容是弹出一个评价列表, 用户选择评价并返回.

注意: `Fragment`有`startActivityForResult`但是没有`setResult`, 可以通过`getActivity.setResult()`来设置传递的参数. `setTargetFragment()`用于当前`Fragment`由别的`Fragment`启动，在完成操作后返回数据的.

```java
public class ContentFragment extends Fragment{
	//...
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		//...
		tv.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				EvaluateDialog dialog = new EvaluateDialog();
				//注意setTargetFragment
				dialog.setTargetFragment(ContentFragment.this, REQUEST_EVALUATE);
				dialog.show(getFragmentManager(), EVALUATE_DIALOG);
			}
		});
		return tv;
	}

	//接收返回回来的数据
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_EVALUATE){
			String evaluate = data
					.getStringExtra(EvaluateDialog.RESPONSE_EVALUATE);
			Toast.makeText(getActivity(), evaluate, Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.putExtra(RESPONSE, evaluate);
			getActivity().setResult(Activity.REQUEST_OK, intent);
		}
	}
}
```

```java
public class EvaluateDialog extends DialogFragment{
	private String[] mEvaluteVals = new String[] { "GOOD", "BAD", "NORMAL" };
	public static final String RESPONSE_EVALUATE = "response_evaluate";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Evaluate :").setItems(mEvaluteVals,
				new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						setResult(which);
					}
				});
		return builder.create();
	}

	// 设置返回数据
	protected void setResult(int which){
		// 判断是否设置了targetFragment
		if (getTargetFragment() == null) return;
		Intent intent = new Intent();
		intent.putExtra(RESPONSE_EVALUATE, mEvaluteVals[which]);
		getTargetFragment().onActivityResult(ContentFragment.REQUEST_EVALUATE,
				Activity.RESULT_OK, intent);

	}
}

```

#### 4. 广播

```java
Intent intent = new Intent("showPro");  
intent.putExtra("name", name);  
LocalBroadcastManager.getInstance(getActivity())  .sendBroadcast(intent);  
```

```java
LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity()); IntentFilter intentFilter = new IntentFilter();  
intentFilter.addAction("showPro");  
BroadcastReceiver br = new BroadcastReceiver() {  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        String key = intent.getStringExtra("name");  
        list = map.get(key);  
        adapter = new ArrayAdapter<String>(getActivity(),  
                android.R.layout.simple_list_item_1, list);  
        lv.setAdapter(adapter);  
    }  
  
};  
localBroadcastManager.registerReceiver(br, intentFilter);  
```



### 参考资料

* [Communicating with Other Fragments](https://developer.android.google.cn/training/basics/fragments/communicating.html#Implement)

* [Android Fragment你应该知道的一切](http://blog.csdn.net/lmj623565791/article/details/42628537/)

* [关于Fragment与Fragment、Activity通讯的四种方式](http://blog.csdn.net/u012702547/article/details/49786417)

  ​


  
