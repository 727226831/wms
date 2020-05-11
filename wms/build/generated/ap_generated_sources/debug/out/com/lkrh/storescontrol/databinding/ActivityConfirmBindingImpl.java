package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityConfirmBindingImpl extends ActivityConfirmBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_cwhcode, 10);
        sViewsWithIds.put(R.id.tv_title1, 11);
        sViewsWithIds.put(R.id.iv_code, 12);
        sViewsWithIds.put(R.id.et_code, 13);
        sViewsWithIds.put(R.id.rl_cdefine10, 14);
        sViewsWithIds.put(R.id.tv_title4, 15);
        sViewsWithIds.put(R.id.iv_cdefine10, 16);
        sViewsWithIds.put(R.id.et_cdefine10, 17);
        sViewsWithIds.put(R.id.tv_count, 18);
        sViewsWithIds.put(R.id.iv_add, 19);
        sViewsWithIds.put(R.id.et_times, 20);
        sViewsWithIds.put(R.id.iv_minus, 21);
        sViewsWithIds.put(R.id.rl_update, 22);
        sViewsWithIds.put(R.id.tv_title3, 23);
        sViewsWithIds.put(R.id.iv_updatecwhcode, 24);
        sViewsWithIds.put(R.id.l_describe, 25);
        sViewsWithIds.put(R.id.b_submit, 26);
        sViewsWithIds.put(R.id.tv_total, 27);
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

    public ActivityConfirmBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }
    private ActivityConfirmBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[26]
            , (android.widget.EditText) bindings[17]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[9]
            , (android.widget.EditText) bindings[6]
            , (android.widget.EditText) bindings[20]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[24]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.RelativeLayout) bindings[14]
            , (android.widget.RelativeLayout) bindings[10]
            , (android.widget.RelativeLayout) bindings[22]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[27]
            );
        this.etDescribe.setTag(null);
        this.etIquantity.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.tvCBatch.setTag(null);
        this.tvCComUnitName.setTag(null);
        this.tvCInvName.setTag(null);
        this.tvCVenName.setTag(null);
        this.tvCinvcode.setTag(null);
        this.tvNumber.setTag(null);
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
            setBean((com.lkrh.storescontrol.bean.ConfirmBean.Formdata) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBean(@Nullable com.lkrh.storescontrol.bean.ConfirmBean.Formdata Bean) {
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
        java.lang.String beanCVenName = null;
        java.lang.String beanCpocode = null;
        java.lang.String beanDescribe = null;
        java.lang.String beanCBatch = null;
        java.lang.String beanCInvName = null;
        java.lang.String beanCComUnitName = null;
        java.lang.String beanIquantity = null;
        com.lkrh.storescontrol.bean.ConfirmBean.Formdata bean = mBean;
        java.lang.String beanCInvCode = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.CVenName
                    beanCVenName = bean.getCVenName();
                    // read bean.cpocode
                    beanCpocode = bean.getCpocode();
                    // read bean.describe
                    beanDescribe = bean.getDescribe();
                    // read bean.CBatch
                    beanCBatch = bean.getCBatch();
                    // read bean.CInvName
                    beanCInvName = bean.getCInvName();
                    // read bean.CComUnitName
                    beanCComUnitName = bean.getCComUnitName();
                    // read bean.iquantity
                    beanIquantity = bean.getIquantity();
                    // read bean.CInvCode
                    beanCInvCode = bean.getCInvCode();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etDescribe, beanDescribe);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etIquantity, beanIquantity);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCBatch, beanCBatch);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCComUnitName, beanCComUnitName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCInvName, beanCInvName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCVenName, beanCVenName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCinvcode, beanCpocode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvNumber, beanCInvCode);
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