package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPackingInspetionBindingImpl extends ActivityPackingInspetionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_cwhcode, 5);
        sViewsWithIds.put(R.id.tv_title1, 6);
        sViewsWithIds.put(R.id.iv_boxcode, 7);
        sViewsWithIds.put(R.id.et_boxcode, 8);
        sViewsWithIds.put(R.id.rl_hscode, 9);
        sViewsWithIds.put(R.id.tv_hscode, 10);
        sViewsWithIds.put(R.id.iv_hscode, 11);
        sViewsWithIds.put(R.id.et_hscode, 12);
        sViewsWithIds.put(R.id.rl_update, 13);
        sViewsWithIds.put(R.id.tv_title3, 14);
        sViewsWithIds.put(R.id.iv_updatecwhcode, 15);
        sViewsWithIds.put(R.id.et_updatecwhcode, 16);
        sViewsWithIds.put(R.id.rl_cdefine10, 17);
        sViewsWithIds.put(R.id.tv_title4, 18);
        sViewsWithIds.put(R.id.iv_cdefine10, 19);
        sViewsWithIds.put(R.id.et_cdefine10, 20);
        sViewsWithIds.put(R.id.rl_transport, 21);
        sViewsWithIds.put(R.id.tv_transport, 22);
        sViewsWithIds.put(R.id.iv_transport, 23);
        sViewsWithIds.put(R.id.et_transport, 24);
        sViewsWithIds.put(R.id.rl_customer, 25);
        sViewsWithIds.put(R.id.tv_title, 26);
        sViewsWithIds.put(R.id.iv_customer, 27);
        sViewsWithIds.put(R.id.et_customer, 28);
        sViewsWithIds.put(R.id.l_cposition, 29);
        sViewsWithIds.put(R.id.tv_cusinvcode, 30);
        sViewsWithIds.put(R.id.rl_iquantityedit, 31);
        sViewsWithIds.put(R.id.tv_title9, 32);
        sViewsWithIds.put(R.id.iv_updatecwhcode9, 33);
        sViewsWithIds.put(R.id.tv_packqty, 34);
        sViewsWithIds.put(R.id.tv_title2, 35);
        sViewsWithIds.put(R.id.iv_code, 36);
        sViewsWithIds.put(R.id.et_code, 37);
        sViewsWithIds.put(R.id.tv_ccodekey, 38);
        sViewsWithIds.put(R.id.tv_count, 39);
        sViewsWithIds.put(R.id.b_end, 40);
        sViewsWithIds.put(R.id.b_search, 41);
        sViewsWithIds.put(R.id.tv_total, 42);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPackingInspetionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 43, sIncludes, sViewsWithIds));
    }
    private ActivityPackingInspetionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[40]
            , (android.widget.Button) bindings[41]
            , (android.widget.EditText) bindings[8]
            , (android.widget.EditText) bindings[20]
            , (android.widget.EditText) bindings[37]
            , (android.widget.EditText) bindings[28]
            , (android.widget.EditText) bindings[12]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[24]
            , (android.widget.EditText) bindings[16]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[36]
            , (android.widget.ImageView) bindings[27]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[23]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[33]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.RelativeLayout) bindings[17]
            , (android.widget.RelativeLayout) bindings[25]
            , (android.widget.RelativeLayout) bindings[5]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[31]
            , (android.widget.RelativeLayout) bindings[21]
            , (android.widget.RelativeLayout) bindings[13]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[35]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[42]
            , (android.widget.TextView) bindings[22]
            );
        this.etIquantity.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.tvCInvStd.setTag(null);
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
        java.lang.String beanCposition = null;
        java.lang.String beanIquantity = null;
        com.lkrh.storescontrol.bean.ArrivalHeadBean bean = mBean;
        java.lang.String beanCInvStd = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.cposition
                    beanCposition = bean.getCposition();
                    // read bean.iquantity
                    beanIquantity = bean.getIquantity();
                    // read bean.cInvStd
                    beanCInvStd = bean.getCInvStd();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etIquantity, beanIquantity);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, beanCposition);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCInvStd, beanCInvStd);
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