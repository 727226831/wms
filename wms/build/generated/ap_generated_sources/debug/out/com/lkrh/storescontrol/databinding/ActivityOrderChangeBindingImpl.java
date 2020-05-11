package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityOrderChangeBindingImpl extends ActivityOrderChangeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_cwhcode, 4);
        sViewsWithIds.put(R.id.tv_title1, 5);
        sViewsWithIds.put(R.id.iv_cwhcode, 6);
        sViewsWithIds.put(R.id.et_order, 7);
        sViewsWithIds.put(R.id.rl_update, 8);
        sViewsWithIds.put(R.id.tv_title3, 9);
        sViewsWithIds.put(R.id.iv_updatecwhcode, 10);
        sViewsWithIds.put(R.id.et_updatecwhcode, 11);
        sViewsWithIds.put(R.id.rl_cdefine10, 12);
        sViewsWithIds.put(R.id.tv_title4, 13);
        sViewsWithIds.put(R.id.iv_cdefine10, 14);
        sViewsWithIds.put(R.id.et_cdefine10, 15);
        sViewsWithIds.put(R.id.tv_title2, 16);
        sViewsWithIds.put(R.id.iv_batch, 17);
        sViewsWithIds.put(R.id.et_count, 18);
        sViewsWithIds.put(R.id.rl_transport, 19);
        sViewsWithIds.put(R.id.tv_transport, 20);
        sViewsWithIds.put(R.id.iv_transport, 21);
        sViewsWithIds.put(R.id.et_transport, 22);
        sViewsWithIds.put(R.id.rl_memo, 23);
        sViewsWithIds.put(R.id.tv_memo, 24);
        sViewsWithIds.put(R.id.et_memo, 25);
        sViewsWithIds.put(R.id.rl_customer, 26);
        sViewsWithIds.put(R.id.tv_title, 27);
        sViewsWithIds.put(R.id.iv_customer, 28);
        sViewsWithIds.put(R.id.et_customer, 29);
        sViewsWithIds.put(R.id.l_cposition, 30);
        sViewsWithIds.put(R.id.rl_iquantityedit, 31);
        sViewsWithIds.put(R.id.tv_title9, 32);
        sViewsWithIds.put(R.id.l_times, 33);
        sViewsWithIds.put(R.id.iv_add, 34);
        sViewsWithIds.put(R.id.et_times, 35);
        sViewsWithIds.put(R.id.iv_minus, 36);
        sViewsWithIds.put(R.id.l_batch, 37);
        sViewsWithIds.put(R.id.tv_cvenbatch, 38);
        sViewsWithIds.put(R.id.ib_upload, 39);
        sViewsWithIds.put(R.id.b_submit, 40);
        sViewsWithIds.put(R.id.tv_total, 41);
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

    public ActivityOrderChangeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 42, sIncludes, sViewsWithIds));
    }
    private ActivityOrderChangeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[40]
            , (android.widget.EditText) bindings[15]
            , (android.widget.EditText) bindings[18]
            , (android.widget.EditText) bindings[29]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[25]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[35]
            , (android.widget.EditText) bindings[22]
            , (android.widget.EditText) bindings[11]
            , (android.widget.ImageButton) bindings[39]
            , (android.widget.ImageView) bindings[34]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[28]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[36]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.LinearLayout) bindings[37]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.RelativeLayout) bindings[12]
            , (android.widget.RelativeLayout) bindings[26]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.RelativeLayout) bindings[31]
            , (android.widget.RelativeLayout) bindings[23]
            , (android.widget.RelativeLayout) bindings[19]
            , (android.widget.RelativeLayout) bindings[8]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[20]
            );
        this.etIquantity.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
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

        if ((dirtyFlags & 0x3L) != 0) {



                if (bean != null) {
                    // read bean.cposition
                    beanCposition = bean.getCposition();
                    // read bean.iquantity
                    beanIquantity = bean.getIquantity();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etIquantity, beanIquantity);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, beanCposition);
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