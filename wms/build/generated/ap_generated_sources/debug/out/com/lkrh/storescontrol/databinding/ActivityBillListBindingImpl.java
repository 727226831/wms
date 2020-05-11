package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityBillListBindingImpl extends ActivityBillListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.l_title, 2);
        sViewsWithIds.put(R.id.l_top, 3);
        sViewsWithIds.put(R.id.l_ccode, 4);
        sViewsWithIds.put(R.id.tv_title1, 5);
        sViewsWithIds.put(R.id.iv_ccode, 6);
        sViewsWithIds.put(R.id.et_ccode, 7);
        sViewsWithIds.put(R.id.iv_clear, 8);
        sViewsWithIds.put(R.id.l_tray, 9);
        sViewsWithIds.put(R.id.tv_tray, 10);
        sViewsWithIds.put(R.id.iv_tray, 11);
        sViewsWithIds.put(R.id.et_tray, 12);
        sViewsWithIds.put(R.id.b_submit, 13);
        sViewsWithIds.put(R.id.b_delete, 14);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityBillListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivityBillListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[14]
            , (android.widget.Button) bindings[13]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[12]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.view.View) bindings[2]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.RelativeLayout) bindings[9]
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[10]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvList.setTag(null);
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
        if (BR.adapter == variableId) {
            setAdapter((com.lkrh.storescontrol.view.declaration.UnqualifiedListActivity.FunctionAdapter) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setAdapter(@Nullable com.lkrh.storescontrol.view.declaration.UnqualifiedListActivity.FunctionAdapter Adapter) {
        this.mAdapter = Adapter;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.adapter);
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
        com.lkrh.storescontrol.view.declaration.UnqualifiedListActivity.FunctionAdapter adapter = mAdapter;

        if ((dirtyFlags & 0x3L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.rvList.setAdapter(adapter);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): adapter
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}