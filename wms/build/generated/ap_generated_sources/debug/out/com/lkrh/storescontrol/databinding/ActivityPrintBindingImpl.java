package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPrintBindingImpl extends ActivityPrintBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_tag, 8);
        sViewsWithIds.put(R.id.iv_code, 9);
        sViewsWithIds.put(R.id.et_page, 10);
        sViewsWithIds.put(R.id.b_print, 11);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPrintBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private ActivityPrintBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[11]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[5]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[10]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.RelativeLayout) bindings[8]
            );
        this.etCInvCName.setTag(null);
        this.etCInvStd.setTag(null);
        this.etCbatch.setTag(null);
        this.etCinvcode.setTag(null);
        this.etCvenabbname.setTag(null);
        this.etGys.setTag(null);
        this.etIquantity.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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
        if (BR.arrivalHeadBean == variableId) {
            setArrivalHeadBean((com.lkrh.storescontrol.bean.ArrivalHeadBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setArrivalHeadBean(@Nullable com.lkrh.storescontrol.bean.ArrivalHeadBean ArrivalHeadBean) {
        this.mArrivalHeadBean = ArrivalHeadBean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.arrivalHeadBean);
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
        java.lang.String arrivalHeadBeanCvenname = null;
        java.lang.String arrivalHeadBeanCInvCode = null;
        java.lang.String arrivalHeadBeanCvenbatch = null;
        java.lang.String arrivalHeadBeanCbatch = null;
        java.lang.String arrivalHeadBeanCInvStd = null;
        java.lang.String arrivalHeadBeanIquantity = null;
        java.lang.String arrivalHeadBeanCInvCName = null;
        com.lkrh.storescontrol.bean.ArrivalHeadBean arrivalHeadBean = mArrivalHeadBean;

        if ((dirtyFlags & 0x3L) != 0) {



                if (arrivalHeadBean != null) {
                    // read arrivalHeadBean.cvenname
                    arrivalHeadBeanCvenname = arrivalHeadBean.getCvenname();
                    // read arrivalHeadBean.cInvCode
                    arrivalHeadBeanCInvCode = arrivalHeadBean.getCInvCode();
                    // read arrivalHeadBean.cvenbatch
                    arrivalHeadBeanCvenbatch = arrivalHeadBean.getCvenbatch();
                    // read arrivalHeadBean.cbatch
                    arrivalHeadBeanCbatch = arrivalHeadBean.getCbatch();
                    // read arrivalHeadBean.cInvStd
                    arrivalHeadBeanCInvStd = arrivalHeadBean.getCInvStd();
                    // read arrivalHeadBean.iquantity
                    arrivalHeadBeanIquantity = arrivalHeadBean.getIquantity();
                    // read arrivalHeadBean.cInvCName
                    arrivalHeadBeanCInvCName = arrivalHeadBean.getCInvCName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etCInvCName, arrivalHeadBeanCInvCName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etCInvStd, arrivalHeadBeanCInvStd);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etCbatch, arrivalHeadBeanCbatch);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etCinvcode, arrivalHeadBeanCInvCode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etCvenabbname, arrivalHeadBeanCvenname);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etGys, arrivalHeadBeanCvenbatch);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etIquantity, arrivalHeadBeanIquantity);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): arrivalHeadBean
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}