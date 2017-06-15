package com.thriftzk.rpc.demo;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-3-7")
public class EchoSerivce {

    public interface Iface {

        public String echo(String msg) throws TException;

    }

    public interface AsyncIface {

        public void echo(String msg, AsyncMethodCallback resultHandler) throws TException;

    }

    public static class Client extends org.apache.thrift.TServiceClient implements Iface {
        public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
            public Factory() {}
            public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
                return new Client(prot);
            }
            public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
                return new Client(iprot, oprot);
            }
        }

        public Client(org.apache.thrift.protocol.TProtocol prot)
        {
            super(prot, prot);
        }

        public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
            super(iprot, oprot);
        }

        public String echo(String msg) throws TException
        {
            send_echo(msg);
            return recv_echo();
        }

        public void send_echo(String msg) throws TException
        {
            echo_args args = new echo_args();
            args.setMsg(msg);
            sendBase("echo", args);
        }

        public String recv_echo() throws TException
        {
            echo_result result = new echo_result();
            receiveBase(result, "echo");
            if (result.isSetSuccess()) {
                return result.success;
            }
            throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "echo failed: unknown result");
        }

    }
    public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
        public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
            private org.apache.thrift.async.TAsyncClientManager clientManager;
            private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
            public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
                this.clientManager = clientManager;
                this.protocolFactory = protocolFactory;
            }
            public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
                return new AsyncClient(protocolFactory, clientManager, transport);
            }
        }

        public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
            super(protocolFactory, clientManager, transport);
        }

        public void echo(String msg, AsyncMethodCallback resultHandler) throws TException {
            checkReady();
            echo_call method_call = new echo_call(msg, resultHandler, this, ___protocolFactory, ___transport);
            this.___currentMethod = method_call;
            ___manager.call(method_call);
        }

        public static class echo_call extends org.apache.thrift.async.TAsyncMethodCall {
            private String msg;
            public echo_call(String msg, AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws TException {
                super(client, protocolFactory, transport, resultHandler, false);
                this.msg = msg;
            }

            public void write_args(org.apache.thrift.protocol.TProtocol prot) throws TException {
                prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("echo", org.apache.thrift.protocol.TMessageType.CALL, 0));
                echo_args args = new echo_args();
                args.setMsg(msg);
                args.write(prot);
                prot.writeMessageEnd();
            }

            public String getResult() throws TException {
                if (getState() != State.RESPONSE_READ) {
                    throw new IllegalStateException("Method call not finished!");
                }
                org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
                org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
                return (new Client(prot)).recv_echo();
            }
        }

    }

    public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
        private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
        public Processor(I iface) {
            super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
        }

        protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
            super(iface, getProcessMap(processMap));
        }

        private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
            processMap.put("echo", new echo());
            return processMap;
        }

        public static class echo<I extends Iface> extends org.apache.thrift.ProcessFunction<I, echo_args> {
            public echo() {
                super("echo");
            }

            public echo_args getEmptyArgsInstance() {
                return new echo_args();
            }

            protected boolean isOneway() {
                return false;
            }

            public echo_result getResult(I iface, echo_args args) throws TException {
                echo_result result = new echo_result();
                result.success = iface.echo(args.msg);
                return result;
            }
        }

    }

    public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
        private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
        public AsyncProcessor(I iface) {
            super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
        }

        protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
            super(iface, getProcessMap(processMap));
        }

        private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
            processMap.put("echo", new echo());
            return processMap;
        }

        public static class echo<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, echo_args, String> {
            public echo() {
                super("echo");
            }

            public echo_args getEmptyArgsInstance() {
                return new echo_args();
            }

            public AsyncMethodCallback<String> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
                final org.apache.thrift.AsyncProcessFunction fcall = this;
                return new AsyncMethodCallback<String>() {
                    public void onComplete(String o) {
                        echo_result result = new echo_result();
                        result.success = o;
                        try {
                            fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
                            return;
                        } catch (Exception e) {
                            LOGGER.error("Exception writing to internal frame buffer", e);
                        }
                        fb.close();
                    }
                    public void onError(Exception e) {
                        byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
                        org.apache.thrift.TBase msg;
                        echo_result result = new echo_result();
                        {
                            msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
                            msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
                        }
                        try {
                            fcall.sendResponse(fb,msg,msgType,seqid);
                            return;
                        } catch (Exception ex) {
                            LOGGER.error("Exception writing to internal frame buffer", ex);
                        }
                        fb.close();
                    }
                };
            }

            protected boolean isOneway() {
                return false;
            }

            public void start(I iface, echo_args args, AsyncMethodCallback<String> resultHandler) throws TException {
                iface.echo(args.msg,resultHandler);
            }
        }

    }

    public static class echo_args implements org.apache.thrift.TBase<echo_args, echo_args._Fields>, java.io.Serializable, Cloneable, Comparable<echo_args>   {
        private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("echo_args");

        private static final org.apache.thrift.protocol.TField MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("msg", org.apache.thrift.protocol.TType.STRING, (short)1);

        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
        static {
            schemes.put(StandardScheme.class, new echo_argsStandardSchemeFactory());
            schemes.put(TupleScheme.class, new echo_argsTupleSchemeFactory());
        }

        public String msg; // required

        /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
        public enum _Fields implements org.apache.thrift.TFieldIdEnum {
            MSG((short)1, "msg");

            private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

            static {
                for (_Fields field : EnumSet.allOf(_Fields.class)) {
                    byName.put(field.getFieldName(), field);
                }
            }

            /**
             * Find the _Fields constant that matches fieldId, or null if its not found.
             */
            public static _Fields findByThriftId(int fieldId) {
                switch(fieldId) {
                    case 1: // MSG
                        return MSG;
                    default:
                        return null;
                }
            }

            /**
             * Find the _Fields constant that matches fieldId, throwing an exception
             * if it is not found.
             */
            public static _Fields findByThriftIdOrThrow(int fieldId) {
                _Fields fields = findByThriftId(fieldId);
                if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
                return fields;
            }

            /**
             * Find the _Fields constant that matches name, or null if its not found.
             */
            public static _Fields findByName(String name) {
                return byName.get(name);
            }

            private final short _thriftId;
            private final String _fieldName;

            _Fields(short thriftId, String fieldName) {
                _thriftId = thriftId;
                _fieldName = fieldName;
            }

            public short getThriftFieldId() {
                return _thriftId;
            }

            public String getFieldName() {
                return _fieldName;
            }
        }

        // isset id assignments
        public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
        static {
            Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
            tmpMap.put(_Fields.MSG, new org.apache.thrift.meta_data.FieldMetaData("msg", org.apache.thrift.TFieldRequirementType.DEFAULT,
                    new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
            metaDataMap = Collections.unmodifiableMap(tmpMap);
            org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(echo_args.class, metaDataMap);
        }

        public echo_args() {
        }

        public echo_args(
                String msg)
        {
            this();
            this.msg = msg;
        }

        /**
         * Performs a deep copy on <i>other</i>.
         */
        public echo_args(echo_args other) {
            if (other.isSetMsg()) {
                this.msg = other.msg;
            }
        }

        public echo_args deepCopy() {
            return new echo_args(this);
        }

        @Override
        public void clear() {
            this.msg = null;
        }

        public String getMsg() {
            return this.msg;
        }

        public echo_args setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public void unsetMsg() {
            this.msg = null;
        }

        /** Returns true if field msg is set (has been assigned a value) and false otherwise */
        public boolean isSetMsg() {
            return this.msg != null;
        }

        public void setMsgIsSet(boolean value) {
            if (!value) {
                this.msg = null;
            }
        }

        public void setFieldValue(_Fields field, Object value) {
            switch (field) {
                case MSG:
                    if (value == null) {
                        unsetMsg();
                    } else {
                        setMsg((String)value);
                    }
                    break;

            }
        }

        public Object getFieldValue(_Fields field) {
            switch (field) {
                case MSG:
                    return getMsg();

            }
            throw new IllegalStateException();
        }

        /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
        public boolean isSet(_Fields field) {
            if (field == null) {
                throw new IllegalArgumentException();
            }

            switch (field) {
                case MSG:
                    return isSetMsg();
            }
            throw new IllegalStateException();
        }

        @Override
        public boolean equals(Object that) {
            if (that == null)
                return false;
            if (that instanceof echo_args)
                return this.equals((echo_args)that);
            return false;
        }

        public boolean equals(echo_args that) {
            if (that == null)
                return false;

            boolean this_present_msg = true && this.isSetMsg();
            boolean that_present_msg = true && that.isSetMsg();
            if (this_present_msg || that_present_msg) {
                if (!(this_present_msg && that_present_msg))
                    return false;
                if (!this.msg.equals(that.msg))
                    return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            List<Object> list = new ArrayList<Object>();

            boolean present_msg = true && (isSetMsg());
            list.add(present_msg);
            if (present_msg)
                list.add(msg);

            return list.hashCode();
        }

        @Override
        public int compareTo(echo_args other) {
            if (!getClass().equals(other.getClass())) {
                return getClass().getName().compareTo(other.getClass().getName());
            }

            int lastComparison = 0;

            lastComparison = Boolean.valueOf(isSetMsg()).compareTo(other.isSetMsg());
            if (lastComparison != 0) {
                return lastComparison;
            }
            if (isSetMsg()) {
                lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.msg, other.msg);
                if (lastComparison != 0) {
                    return lastComparison;
                }
            }
            return 0;
        }

        public _Fields fieldForId(int fieldId) {
            return _Fields.findByThriftId(fieldId);
        }

        public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
            schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
            schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("echo_args(");
            boolean first = true;

            sb.append("msg:");
            if (this.msg == null) {
                sb.append("null");
            } else {
                sb.append(this.msg);
            }
            first = false;
            sb.append(")");
            return sb.toString();
        }

        public void validate() throws TException {
            // check for required fields
            // check for sub-struct validity
        }

        private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
            try {
                write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
            } catch (TException te) {
                throw new java.io.IOException(te);
            }
        }

        private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
            try {
                read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
            } catch (TException te) {
                throw new java.io.IOException(te);
            }
        }

        private static class echo_argsStandardSchemeFactory implements SchemeFactory {
            public echo_argsStandardScheme getScheme() {
                return new echo_argsStandardScheme();
            }
        }

        private static class echo_argsStandardScheme extends StandardScheme<echo_args> {

            public void read(org.apache.thrift.protocol.TProtocol iprot, echo_args struct) throws TException {
                org.apache.thrift.protocol.TField schemeField;
                iprot.readStructBegin();
                while (true)
                {
                    schemeField = iprot.readFieldBegin();
                    if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                        break;
                    }
                    switch (schemeField.id) {
                        case 1: // MSG
                            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                                struct.msg = iprot.readString();
                                struct.setMsgIsSet(true);
                            } else {
                                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                            }
                            break;
                        default:
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                    }
                    iprot.readFieldEnd();
                }
                iprot.readStructEnd();

                // check for required fields of primitive type, which can't be checked in the validate method
                struct.validate();
            }

            public void write(org.apache.thrift.protocol.TProtocol oprot, echo_args struct) throws TException {
                struct.validate();

                oprot.writeStructBegin(STRUCT_DESC);
                if (struct.msg != null) {
                    oprot.writeFieldBegin(MSG_FIELD_DESC);
                    oprot.writeString(struct.msg);
                    oprot.writeFieldEnd();
                }
                oprot.writeFieldStop();
                oprot.writeStructEnd();
            }

        }

        private static class echo_argsTupleSchemeFactory implements SchemeFactory {
            public echo_argsTupleScheme getScheme() {
                return new echo_argsTupleScheme();
            }
        }

        private static class echo_argsTupleScheme extends TupleScheme<echo_args> {

            @Override
            public void write(org.apache.thrift.protocol.TProtocol prot, echo_args struct) throws TException {
                TTupleProtocol oprot = (TTupleProtocol) prot;
                BitSet optionals = new BitSet();
                if (struct.isSetMsg()) {
                    optionals.set(0);
                }
                oprot.writeBitSet(optionals, 1);
                if (struct.isSetMsg()) {
                    oprot.writeString(struct.msg);
                }
            }

            @Override
            public void read(org.apache.thrift.protocol.TProtocol prot, echo_args struct) throws TException {
                TTupleProtocol iprot = (TTupleProtocol) prot;
                BitSet incoming = iprot.readBitSet(1);
                if (incoming.get(0)) {
                    struct.msg = iprot.readString();
                    struct.setMsgIsSet(true);
                }
            }
        }

    }

    public static class echo_result implements org.apache.thrift.TBase<echo_result, echo_result._Fields>, java.io.Serializable, Cloneable, Comparable<echo_result>   {
        private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("echo_result");

        private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRING, (short)0);

        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
        static {
            schemes.put(StandardScheme.class, new echo_resultStandardSchemeFactory());
            schemes.put(TupleScheme.class, new echo_resultTupleSchemeFactory());
        }

        public String success; // required

        /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
        public enum _Fields implements org.apache.thrift.TFieldIdEnum {
            SUCCESS((short)0, "success");

            private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

            static {
                for (_Fields field : EnumSet.allOf(_Fields.class)) {
                    byName.put(field.getFieldName(), field);
                }
            }

            /**
             * Find the _Fields constant that matches fieldId, or null if its not found.
             */
            public static _Fields findByThriftId(int fieldId) {
                switch(fieldId) {
                    case 0: // SUCCESS
                        return SUCCESS;
                    default:
                        return null;
                }
            }

            /**
             * Find the _Fields constant that matches fieldId, throwing an exception
             * if it is not found.
             */
            public static _Fields findByThriftIdOrThrow(int fieldId) {
                _Fields fields = findByThriftId(fieldId);
                if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
                return fields;
            }

            /**
             * Find the _Fields constant that matches name, or null if its not found.
             */
            public static _Fields findByName(String name) {
                return byName.get(name);
            }

            private final short _thriftId;
            private final String _fieldName;

            _Fields(short thriftId, String fieldName) {
                _thriftId = thriftId;
                _fieldName = fieldName;
            }

            public short getThriftFieldId() {
                return _thriftId;
            }

            public String getFieldName() {
                return _fieldName;
            }
        }

        // isset id assignments
        public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
        static {
            Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
            tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT,
                    new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
            metaDataMap = Collections.unmodifiableMap(tmpMap);
            org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(echo_result.class, metaDataMap);
        }

        public echo_result() {
        }

        public echo_result(
                String success)
        {
            this();
            this.success = success;
        }

        /**
         * Performs a deep copy on <i>other</i>.
         */
        public echo_result(echo_result other) {
            if (other.isSetSuccess()) {
                this.success = other.success;
            }
        }

        public echo_result deepCopy() {
            return new echo_result(this);
        }

        @Override
        public void clear() {
            this.success = null;
        }

        public String getSuccess() {
            return this.success;
        }

        public echo_result setSuccess(String success) {
            this.success = success;
            return this;
        }

        public void unsetSuccess() {
            this.success = null;
        }

        /** Returns true if field success is set (has been assigned a value) and false otherwise */
        public boolean isSetSuccess() {
            return this.success != null;
        }

        public void setSuccessIsSet(boolean value) {
            if (!value) {
                this.success = null;
            }
        }

        public void setFieldValue(_Fields field, Object value) {
            switch (field) {
                case SUCCESS:
                    if (value == null) {
                        unsetSuccess();
                    } else {
                        setSuccess((String)value);
                    }
                    break;

            }
        }

        public Object getFieldValue(_Fields field) {
            switch (field) {
                case SUCCESS:
                    return getSuccess();

            }
            throw new IllegalStateException();
        }

        /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
        public boolean isSet(_Fields field) {
            if (field == null) {
                throw new IllegalArgumentException();
            }

            switch (field) {
                case SUCCESS:
                    return isSetSuccess();
            }
            throw new IllegalStateException();
        }

        @Override
        public boolean equals(Object that) {
            if (that == null)
                return false;
            if (that instanceof echo_result)
                return this.equals((echo_result)that);
            return false;
        }

        public boolean equals(echo_result that) {
            if (that == null)
                return false;

            boolean this_present_success = true && this.isSetSuccess();
            boolean that_present_success = true && that.isSetSuccess();
            if (this_present_success || that_present_success) {
                if (!(this_present_success && that_present_success))
                    return false;
                if (!this.success.equals(that.success))
                    return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            List<Object> list = new ArrayList<Object>();

            boolean present_success = true && (isSetSuccess());
            list.add(present_success);
            if (present_success)
                list.add(success);

            return list.hashCode();
        }

        @Override
        public int compareTo(echo_result other) {
            if (!getClass().equals(other.getClass())) {
                return getClass().getName().compareTo(other.getClass().getName());
            }

            int lastComparison = 0;

            lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
                return lastComparison;
            }
            if (isSetSuccess()) {
                lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
                if (lastComparison != 0) {
                    return lastComparison;
                }
            }
            return 0;
        }

        public _Fields fieldForId(int fieldId) {
            return _Fields.findByThriftId(fieldId);
        }

        public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
            schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
            schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("echo_result(");
            boolean first = true;

            sb.append("success:");
            if (this.success == null) {
                sb.append("null");
            } else {
                sb.append(this.success);
            }
            first = false;
            sb.append(")");
            return sb.toString();
        }

        public void validate() throws TException {
            // check for required fields
            // check for sub-struct validity
        }

        private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
            try {
                write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
            } catch (TException te) {
                throw new java.io.IOException(te);
            }
        }

        private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
            try {
                read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
            } catch (TException te) {
                throw new java.io.IOException(te);
            }
        }

        private static class echo_resultStandardSchemeFactory implements SchemeFactory {
            public echo_resultStandardScheme getScheme() {
                return new echo_resultStandardScheme();
            }
        }

        private static class echo_resultStandardScheme extends StandardScheme<echo_result> {

            public void read(org.apache.thrift.protocol.TProtocol iprot, echo_result struct) throws TException {
                org.apache.thrift.protocol.TField schemeField;
                iprot.readStructBegin();
                while (true)
                {
                    schemeField = iprot.readFieldBegin();
                    if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                        break;
                    }
                    switch (schemeField.id) {
                        case 0: // SUCCESS
                            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                                struct.success = iprot.readString();
                                struct.setSuccessIsSet(true);
                            } else {
                                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                            }
                            break;
                        default:
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                    }
                    iprot.readFieldEnd();
                }
                iprot.readStructEnd();

                // check for required fields of primitive type, which can't be checked in the validate method
                struct.validate();
            }

            public void write(org.apache.thrift.protocol.TProtocol oprot, echo_result struct) throws TException {
                struct.validate();

                oprot.writeStructBegin(STRUCT_DESC);
                if (struct.success != null) {
                    oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
                    oprot.writeString(struct.success);
                    oprot.writeFieldEnd();
                }
                oprot.writeFieldStop();
                oprot.writeStructEnd();
            }

        }

        private static class echo_resultTupleSchemeFactory implements SchemeFactory {
            public echo_resultTupleScheme getScheme() {
                return new echo_resultTupleScheme();
            }
        }

        private static class echo_resultTupleScheme extends TupleScheme<echo_result> {

            @Override
            public void write(org.apache.thrift.protocol.TProtocol prot, echo_result struct) throws TException {
                TTupleProtocol oprot = (TTupleProtocol) prot;
                BitSet optionals = new BitSet();
                if (struct.isSetSuccess()) {
                    optionals.set(0);
                }
                oprot.writeBitSet(optionals, 1);
                if (struct.isSetSuccess()) {
                    oprot.writeString(struct.success);
                }
            }

            @Override
            public void read(org.apache.thrift.protocol.TProtocol prot, echo_result struct) throws TException {
                TTupleProtocol iprot = (TTupleProtocol) prot;
                BitSet incoming = iprot.readBitSet(1);
                if (incoming.get(0)) {
                    struct.success = iprot.readString();
                    struct.setSuccessIsSet(true);
                }
            }
        }

    }

}

