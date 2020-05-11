package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPutdetailBindingImpl extends ActivityPutdetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_title1, 7);
        sViewsWithIds.put(R.id.et_cwhcode, 8);
        sViewsWithIds.put(R.id.iv_cwhcode, 9);
        sViewsWithIds.put(R.id.tv_title2, 10);
        sViewsWithIds.put(R.id.et_batch, 11);
        sViewsWithIds.put(R.id.iv_batch, 12);
        sViewsWithIds.put(R.id.tv_count, 13);
        sViewsWithIds.put(R.id.tv_number, 14);
        sViewsWithIds.put(R.id.iv_add, 15);
        sViewsWithIds.put(R.id.et_times, 16);
        sViewsWithIds.put(R.id.iv_minus, 17);
        sViewsWithIds.put(R.id.l_cvenabbname, 18);
        sViewsWithIds.put(R.id.b_submit, 19);
        sViewsWithIds.put(R.id.b_search, 20);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPutdetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ActivityPutdetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[20]
            , (android.widget.Button) bindings[19]
            , (android.widget.EditText) bindings[11]
            , (android.widget.EditText) bindings[8]
            , (android.widget.EditText) bindings[16]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[10]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.tvCInvName.setTag(null);
        this.tvCInvStd.setTag(null);
        this.tvCcode.setTag(null);
        this.tvCinvcode.setTag(null);
        this.tvCvenabbname.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.bean == variableId) {
            setBean((com.lkrh.storescontrol.bean.ArrivalHeadBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBean(@Nullable com.lkrh.storescontrol.bean.ArrivalHeadBean Bean) {
        this.mBean = Bean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.bean);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String beanCbatch = null;
        java.lang.String beanCvenabbname = null;
        java.lang.String beanCInvName = null;
        java.lang.String beanCcode = null;
        com.lkrh.storescontrol.bean.ArrivalHeadBean bean = mBean;
        java.lang.String beanCInvStd = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.cbatch
                    beanCbatch = bean.getCbatch();
                    // read bean.cvenabbname
                    beanCvenabbname = bean.getCvenabbname();
                    // read bean.cInvName
                    beanCInvName = bean.getCInvName();
                    // read bean.ccode
                    beanCcode = bean.getCcode();
                    // read bean.cInvStd
                    beanCInvStd = bean.getCInvStd();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCInvName, beanCInvName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCInvStd, beanCInvStd);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCcode, beanCcode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCinvcode, beanCbatch);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCvenabbname, beanCvenabbname);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): bean
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}