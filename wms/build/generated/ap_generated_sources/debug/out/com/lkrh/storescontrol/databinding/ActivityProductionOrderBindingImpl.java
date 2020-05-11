package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProductionOrderBindingImpl extends ActivityProductionOrderBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_ordertype, 2);
        sViewsWithIds.put(R.id.rg_ordertype, 3);
        sViewsWithIds.put(R.id.rb_standard, 4);
        sViewsWithIds.put(R.id.rb_unstandard, 5);
        sViewsWithIds.put(R.id.tv_orderclass, 6);
        sViewsWithIds.put(R.id.rg_orderclass, 7);
        sViewsWithIds.put(R.id.rb_orderclass1, 8);
        sViewsWithIds.put(R.id.rb_orderclass2, 9);
        sViewsWithIds.put(R.id.rb_orderclass3, 10);
        sViewsWithIds.put(R.id.rb_orderclass4, 11);
        sViewsWithIds.put(R.id.rb_orderclass5, 12);
        sViewsWithIds.put(R.id.rb_orderclass6, 13);
        sViewsWithIds.put(R.id.tv_title2, 14);
        sViewsWithIds.put(R.id.et_depcode, 15);
        sViewsWithIds.put(R.id.tv_title1, 16);
        sViewsWithIds.put(R.id.et_invcode, 17);
        sViewsWithIds.put(R.id.rl_quantity, 18);
        sViewsWithIds.put(R.id.tv_quantity, 19);
        sViewsWithIds.put(R.id.et_quantity, 20);
        sViewsWithIds.put(R.id.rl_memo, 21);
        sViewsWithIds.put(R.id.tv_memo, 22);
        sViewsWithIds.put(R.id.et_memo, 23);
        sViewsWithIds.put(R.id.b_submit, 24);
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

    public ActivityProductionOrderBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private ActivityProductionOrderBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[24]
            , (android.widget.EditText) bindings[15]
            , (android.widget.EditText) bindings[17]
            , (android.widget.EditText) bindings[23]
            , (android.widget.EditText) bindings[20]
            , (android.widget.RadioButton) bindings[8]
            , (android.widget.RadioButton) bindings[9]
            , (android.widget.RadioButton) bindings[10]
            , (android.widget.RadioButton) bindings[11]
            , (android.widget.RadioButton) bindings[12]
            , (android.widget.RadioButton) bindings[13]
            , (android.widget.RadioButton) bindings[4]
            , (android.widget.RadioButton) bindings[5]
            , (android.widget.RadioGroup) bindings[7]
            , (android.widget.RadioGroup) bindings[3]
            , (android.widget.RelativeLayout) bindings[21]
            , (android.widget.RelativeLayout) bindings[18]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[14]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}