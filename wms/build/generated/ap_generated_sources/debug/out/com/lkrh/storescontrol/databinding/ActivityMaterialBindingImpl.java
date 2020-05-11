package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMaterialBindingImpl extends ActivityMaterialBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_title1, 2);
        sViewsWithIds.put(R.id.iv_scan, 3);
        sViewsWithIds.put(R.id.et_cwhcode, 4);
        sViewsWithIds.put(R.id.tv_cinvname, 5);
        sViewsWithIds.put(R.id.et_iqty, 6);
        sViewsWithIds.put(R.id.tv_cbatch, 7);
        sViewsWithIds.put(R.id.et_batch, 8);
        sViewsWithIds.put(R.id.tv_cvenbatch, 9);
        sViewsWithIds.put(R.id.tv_moqty, 10);
        sViewsWithIds.put(R.id.tv_cinvcode, 11);
        sViewsWithIds.put(R.id.tv_cInvStd, 12);
        sViewsWithIds.put(R.id.rg_cposcode, 13);
        sViewsWithIds.put(R.id.rb_cposcode, 14);
        sViewsWithIds.put(R.id.b_submit, 15);
        sViewsWithIds.put(R.id.b_material, 16);
        sViewsWithIds.put(R.id.rv_list, 17);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMaterialBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private ActivityMaterialBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[16]
            , (android.widget.Button) bindings[15]
            , (android.widget.TextView) bindings[8]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[6]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.RadioButton) bindings[14]
            , (android.widget.RadioGroup) bindings[13]
            , (androidx.recyclerview.widget.RecyclerView) bindings[17]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[2]
            );
        this.lInput.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
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
        // batch finished
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