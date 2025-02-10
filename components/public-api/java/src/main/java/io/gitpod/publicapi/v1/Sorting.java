// Copyright (c) 2025 Gitpod GmbH. All rights reserved.
// Licensed under the GNU Affero General Public License (AGPL).
// See License.AGPL.txt in the project root for license information.

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: gitpod/v1/sorting.proto
// Protobuf Java Version: 4.27.2

package io.gitpod.publicapi.v1;

public final class Sorting {
  private Sorting() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 2,
      /* suffix= */ "",
      Sorting.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code gitpod.v1.SortOrder}
   */
  public enum SortOrder
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>SORT_ORDER_UNSPECIFIED = 0;</code>
     */
    SORT_ORDER_UNSPECIFIED(0),
    /**
     * <code>SORT_ORDER_ASC = 1;</code>
     */
    SORT_ORDER_ASC(1),
    /**
     * <code>SORT_ORDER_DESC = 2;</code>
     */
    SORT_ORDER_DESC(2),
    UNRECOGNIZED(-1),
    ;

    static {
      com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
        com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
        /* major= */ 4,
        /* minor= */ 27,
        /* patch= */ 2,
        /* suffix= */ "",
        SortOrder.class.getName());
    }
    /**
     * <code>SORT_ORDER_UNSPECIFIED = 0;</code>
     */
    public static final int SORT_ORDER_UNSPECIFIED_VALUE = 0;
    /**
     * <code>SORT_ORDER_ASC = 1;</code>
     */
    public static final int SORT_ORDER_ASC_VALUE = 1;
    /**
     * <code>SORT_ORDER_DESC = 2;</code>
     */
    public static final int SORT_ORDER_DESC_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static SortOrder valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static SortOrder forNumber(int value) {
      switch (value) {
        case 0: return SORT_ORDER_UNSPECIFIED;
        case 1: return SORT_ORDER_ASC;
        case 2: return SORT_ORDER_DESC;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<SortOrder>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        SortOrder> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<SortOrder>() {
            public SortOrder findValueByNumber(int number) {
              return SortOrder.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return io.gitpod.publicapi.v1.Sorting.getDescriptor().getEnumTypes().get(0);
    }

    private static final SortOrder[] VALUES = values();

    public static SortOrder valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private SortOrder(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:gitpod.v1.SortOrder)
  }

  public interface SortOrBuilder extends
      // @@protoc_insertion_point(interface_extends:gitpod.v1.Sort)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Field name to sort by, in camelCase.
     * </pre>
     *
     * <code>string field = 1 [json_name = "field"];</code>
     * @return The field.
     */
    java.lang.String getField();
    /**
     * <pre>
     * Field name to sort by, in camelCase.
     * </pre>
     *
     * <code>string field = 1 [json_name = "field"];</code>
     * @return The bytes for field.
     */
    com.google.protobuf.ByteString
        getFieldBytes();

    /**
     * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
     * @return The enum numeric value on the wire for order.
     */
    int getOrderValue();
    /**
     * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
     * @return The order.
     */
    io.gitpod.publicapi.v1.Sorting.SortOrder getOrder();
  }
  /**
   * Protobuf type {@code gitpod.v1.Sort}
   */
  public static final class Sort extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:gitpod.v1.Sort)
      SortOrBuilder {
  private static final long serialVersionUID = 0L;
    static {
      com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
        com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
        /* major= */ 4,
        /* minor= */ 27,
        /* patch= */ 2,
        /* suffix= */ "",
        Sort.class.getName());
    }
    // Use Sort.newBuilder() to construct.
    private Sort(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
    }
    private Sort() {
      field_ = "";
      order_ = 0;
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.gitpod.publicapi.v1.Sorting.internal_static_gitpod_v1_Sort_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.gitpod.publicapi.v1.Sorting.internal_static_gitpod_v1_Sort_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.gitpod.publicapi.v1.Sorting.Sort.class, io.gitpod.publicapi.v1.Sorting.Sort.Builder.class);
    }

    public static final int FIELD_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object field_ = "";
    /**
     * <pre>
     * Field name to sort by, in camelCase.
     * </pre>
     *
     * <code>string field = 1 [json_name = "field"];</code>
     * @return The field.
     */
    @java.lang.Override
    public java.lang.String getField() {
      java.lang.Object ref = field_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        field_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Field name to sort by, in camelCase.
     * </pre>
     *
     * <code>string field = 1 [json_name = "field"];</code>
     * @return The bytes for field.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getFieldBytes() {
      java.lang.Object ref = field_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        field_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ORDER_FIELD_NUMBER = 2;
    private int order_ = 0;
    /**
     * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
     * @return The enum numeric value on the wire for order.
     */
    @java.lang.Override public int getOrderValue() {
      return order_;
    }
    /**
     * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
     * @return The order.
     */
    @java.lang.Override public io.gitpod.publicapi.v1.Sorting.SortOrder getOrder() {
      io.gitpod.publicapi.v1.Sorting.SortOrder result = io.gitpod.publicapi.v1.Sorting.SortOrder.forNumber(order_);
      return result == null ? io.gitpod.publicapi.v1.Sorting.SortOrder.UNRECOGNIZED : result;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(field_)) {
        com.google.protobuf.GeneratedMessage.writeString(output, 1, field_);
      }
      if (order_ != io.gitpod.publicapi.v1.Sorting.SortOrder.SORT_ORDER_UNSPECIFIED.getNumber()) {
        output.writeEnum(2, order_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessage.isStringEmpty(field_)) {
        size += com.google.protobuf.GeneratedMessage.computeStringSize(1, field_);
      }
      if (order_ != io.gitpod.publicapi.v1.Sorting.SortOrder.SORT_ORDER_UNSPECIFIED.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, order_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof io.gitpod.publicapi.v1.Sorting.Sort)) {
        return super.equals(obj);
      }
      io.gitpod.publicapi.v1.Sorting.Sort other = (io.gitpod.publicapi.v1.Sorting.Sort) obj;

      if (!getField()
          .equals(other.getField())) return false;
      if (order_ != other.order_) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + FIELD_FIELD_NUMBER;
      hash = (53 * hash) + getField().hashCode();
      hash = (37 * hash) + ORDER_FIELD_NUMBER;
      hash = (53 * hash) + order_;
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static io.gitpod.publicapi.v1.Sorting.Sort parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static io.gitpod.publicapi.v1.Sorting.Sort parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static io.gitpod.publicapi.v1.Sorting.Sort parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(io.gitpod.publicapi.v1.Sorting.Sort prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code gitpod.v1.Sort}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:gitpod.v1.Sort)
        io.gitpod.publicapi.v1.Sorting.SortOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return io.gitpod.publicapi.v1.Sorting.internal_static_gitpod_v1_Sort_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return io.gitpod.publicapi.v1.Sorting.internal_static_gitpod_v1_Sort_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                io.gitpod.publicapi.v1.Sorting.Sort.class, io.gitpod.publicapi.v1.Sorting.Sort.Builder.class);
      }

      // Construct using io.gitpod.publicapi.v1.Sorting.Sort.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        field_ = "";
        order_ = 0;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return io.gitpod.publicapi.v1.Sorting.internal_static_gitpod_v1_Sort_descriptor;
      }

      @java.lang.Override
      public io.gitpod.publicapi.v1.Sorting.Sort getDefaultInstanceForType() {
        return io.gitpod.publicapi.v1.Sorting.Sort.getDefaultInstance();
      }

      @java.lang.Override
      public io.gitpod.publicapi.v1.Sorting.Sort build() {
        io.gitpod.publicapi.v1.Sorting.Sort result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public io.gitpod.publicapi.v1.Sorting.Sort buildPartial() {
        io.gitpod.publicapi.v1.Sorting.Sort result = new io.gitpod.publicapi.v1.Sorting.Sort(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(io.gitpod.publicapi.v1.Sorting.Sort result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.field_ = field_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.order_ = order_;
        }
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof io.gitpod.publicapi.v1.Sorting.Sort) {
          return mergeFrom((io.gitpod.publicapi.v1.Sorting.Sort)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(io.gitpod.publicapi.v1.Sorting.Sort other) {
        if (other == io.gitpod.publicapi.v1.Sorting.Sort.getDefaultInstance()) return this;
        if (!other.getField().isEmpty()) {
          field_ = other.field_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (other.order_ != 0) {
          setOrderValue(other.getOrderValue());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                field_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 16: {
                order_ = input.readEnum();
                bitField0_ |= 0x00000002;
                break;
              } // case 16
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object field_ = "";
      /**
       * <pre>
       * Field name to sort by, in camelCase.
       * </pre>
       *
       * <code>string field = 1 [json_name = "field"];</code>
       * @return The field.
       */
      public java.lang.String getField() {
        java.lang.Object ref = field_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          field_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Field name to sort by, in camelCase.
       * </pre>
       *
       * <code>string field = 1 [json_name = "field"];</code>
       * @return The bytes for field.
       */
      public com.google.protobuf.ByteString
          getFieldBytes() {
        java.lang.Object ref = field_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          field_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Field name to sort by, in camelCase.
       * </pre>
       *
       * <code>string field = 1 [json_name = "field"];</code>
       * @param value The field to set.
       * @return This builder for chaining.
       */
      public Builder setField(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        field_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Field name to sort by, in camelCase.
       * </pre>
       *
       * <code>string field = 1 [json_name = "field"];</code>
       * @return This builder for chaining.
       */
      public Builder clearField() {
        field_ = getDefaultInstance().getField();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Field name to sort by, in camelCase.
       * </pre>
       *
       * <code>string field = 1 [json_name = "field"];</code>
       * @param value The bytes for field to set.
       * @return This builder for chaining.
       */
      public Builder setFieldBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        field_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private int order_ = 0;
      /**
       * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
       * @return The enum numeric value on the wire for order.
       */
      @java.lang.Override public int getOrderValue() {
        return order_;
      }
      /**
       * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
       * @param value The enum numeric value on the wire for order to set.
       * @return This builder for chaining.
       */
      public Builder setOrderValue(int value) {
        order_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
       * @return The order.
       */
      @java.lang.Override
      public io.gitpod.publicapi.v1.Sorting.SortOrder getOrder() {
        io.gitpod.publicapi.v1.Sorting.SortOrder result = io.gitpod.publicapi.v1.Sorting.SortOrder.forNumber(order_);
        return result == null ? io.gitpod.publicapi.v1.Sorting.SortOrder.UNRECOGNIZED : result;
      }
      /**
       * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
       * @param value The order to set.
       * @return This builder for chaining.
       */
      public Builder setOrder(io.gitpod.publicapi.v1.Sorting.SortOrder value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        order_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.gitpod.v1.SortOrder order = 2 [json_name = "order"];</code>
       * @return This builder for chaining.
       */
      public Builder clearOrder() {
        bitField0_ = (bitField0_ & ~0x00000002);
        order_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:gitpod.v1.Sort)
    }

    // @@protoc_insertion_point(class_scope:gitpod.v1.Sort)
    private static final io.gitpod.publicapi.v1.Sorting.Sort DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new io.gitpod.publicapi.v1.Sorting.Sort();
    }

    public static io.gitpod.publicapi.v1.Sorting.Sort getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Sort>
        PARSER = new com.google.protobuf.AbstractParser<Sort>() {
      @java.lang.Override
      public Sort parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Sort> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Sort> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public io.gitpod.publicapi.v1.Sorting.Sort getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_gitpod_v1_Sort_descriptor;
  private static final
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_gitpod_v1_Sort_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027gitpod/v1/sorting.proto\022\tgitpod.v1\"H\n\004" +
      "Sort\022\024\n\005field\030\001 \001(\tR\005field\022*\n\005order\030\002 \001(" +
      "\0162\024.gitpod.v1.SortOrderR\005order*P\n\tSortOr" +
      "der\022\032\n\026SORT_ORDER_UNSPECIFIED\020\000\022\022\n\016SORT_" +
      "ORDER_ASC\020\001\022\023\n\017SORT_ORDER_DESC\020\002BQ\n\026io.g" +
      "itpod.publicapi.v1Z7github.com/gitpod-io" +
      "/gitpod/components/public-api/go/v1b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_gitpod_v1_Sort_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_gitpod_v1_Sort_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_gitpod_v1_Sort_descriptor,
        new java.lang.String[] { "Field", "Order", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
