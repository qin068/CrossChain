// Code generated by protoc-gen-gogo. DO NOT EDIT.
// source: blog/timedout_post.proto

package types

import (
	fmt "fmt"
	_ "github.com/gogo/protobuf/gogoproto"
	proto "github.com/gogo/protobuf/proto"
	io "io"
	math "math"
	math_bits "math/bits"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.GoGoProtoPackageIsVersion3 // please upgrade the proto package

type TimedoutPost struct {
	Creator string `protobuf:"bytes,1,opt,name=creator,proto3" json:"creator,omitempty"`
	Id      uint64 `protobuf:"varint,2,opt,name=id,proto3" json:"id,omitempty"`
	Title   string `protobuf:"bytes,3,opt,name=title,proto3" json:"title,omitempty"`
	Chain   string `protobuf:"bytes,4,opt,name=chain,proto3" json:"chain,omitempty"`
	Sender  string `protobuf:"bytes,5,opt,name=sender,proto3" json:"sender,omitempty"`
}

func (m *TimedoutPost) Reset()         { *m = TimedoutPost{} }
func (m *TimedoutPost) String() string { return proto.CompactTextString(m) }
func (*TimedoutPost) ProtoMessage()    {}
func (*TimedoutPost) Descriptor() ([]byte, []int) {
	return fileDescriptor_684c1eec21adcd26, []int{0}
}
func (m *TimedoutPost) XXX_Unmarshal(b []byte) error {
	return m.Unmarshal(b)
}
func (m *TimedoutPost) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	if deterministic {
		return xxx_messageInfo_TimedoutPost.Marshal(b, m, deterministic)
	} else {
		b = b[:cap(b)]
		n, err := m.MarshalToSizedBuffer(b)
		if err != nil {
			return nil, err
		}
		return b[:n], nil
	}
}
func (m *TimedoutPost) XXX_Merge(src proto.Message) {
	xxx_messageInfo_TimedoutPost.Merge(m, src)
}
func (m *TimedoutPost) XXX_Size() int {
	return m.Size()
}
func (m *TimedoutPost) XXX_DiscardUnknown() {
	xxx_messageInfo_TimedoutPost.DiscardUnknown(m)
}

var xxx_messageInfo_TimedoutPost proto.InternalMessageInfo

func (m *TimedoutPost) GetCreator() string {
	if m != nil {
		return m.Creator
	}
	return ""
}

func (m *TimedoutPost) GetId() uint64 {
	if m != nil {
		return m.Id
	}
	return 0
}

func (m *TimedoutPost) GetTitle() string {
	if m != nil {
		return m.Title
	}
	return ""
}

func (m *TimedoutPost) GetChain() string {
	if m != nil {
		return m.Chain
	}
	return ""
}

func (m *TimedoutPost) GetSender() string {
	if m != nil {
		return m.Sender
	}
	return ""
}

func init() {
	proto.RegisterType((*TimedoutPost)(nil), "user.interchange.blog.TimedoutPost")
}

func init() { proto.RegisterFile("blog/timedout_post.proto", fileDescriptor_684c1eec21adcd26) }

var fileDescriptor_684c1eec21adcd26 = []byte{
	// 232 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0x4c, 0x8f, 0x4f, 0x4a, 0xc4, 0x30,
	0x14, 0x87, 0x9b, 0x3a, 0x33, 0x62, 0x10, 0x17, 0x61, 0x94, 0xe0, 0x22, 0x0c, 0xae, 0xba, 0x6a,
	0x16, 0xde, 0x60, 0x4e, 0x20, 0x83, 0x2b, 0x37, 0xd2, 0x3f, 0x8f, 0x34, 0x30, 0xd3, 0x57, 0x92,
	0x57, 0x50, 0xf0, 0x10, 0x1e, 0xcb, 0xe5, 0x2c, 0x5d, 0x4a, 0x7b, 0x11, 0x49, 0x52, 0xc1, 0xdd,
	0xfb, 0x7e, 0x7c, 0x3c, 0xf8, 0xb8, 0xac, 0x8f, 0x68, 0x34, 0xd9, 0x13, 0xb4, 0x38, 0xd2, 0xeb,
	0x80, 0x9e, 0xca, 0xc1, 0x21, 0xa1, 0xb8, 0x1d, 0x3d, 0xb8, 0xd2, 0xf6, 0x04, 0xae, 0xe9, 0xaa,
	0xde, 0x40, 0x19, 0xd4, 0xfb, 0xad, 0x41, 0x83, 0xd1, 0xd0, 0xe1, 0x4a, 0xf2, 0xc3, 0x07, 0xbf,
	0x7e, 0x5e, 0x7e, 0x3c, 0xa1, 0x27, 0x21, 0xf9, 0x65, 0xe3, 0xa0, 0x22, 0x74, 0x92, 0xed, 0x58,
	0x71, 0x75, 0xf8, 0x43, 0x71, 0xc3, 0x73, 0xdb, 0xca, 0x7c, 0xc7, 0x8a, 0xd5, 0x21, 0xb7, 0xad,
	0xd8, 0xf2, 0x35, 0x59, 0x3a, 0x82, 0xbc, 0x88, 0x5e, 0x82, 0xb0, 0x36, 0x5d, 0x65, 0x7b, 0xb9,
	0x4a, 0x6b, 0x04, 0x71, 0xc7, 0x37, 0x1e, 0xfa, 0x16, 0x9c, 0x5c, 0xc7, 0x79, 0xa1, 0xfd, 0xfe,
	0x6b, 0x52, 0xec, 0x3c, 0x29, 0xf6, 0x33, 0x29, 0xf6, 0x39, 0xab, 0xec, 0x3c, 0xab, 0xec, 0x7b,
	0x56, 0xd9, 0x4b, 0x61, 0x2c, 0x75, 0x63, 0x5d, 0x36, 0x78, 0xd2, 0xa1, 0x47, 0xff, 0xeb, 0xd1,
	0x6f, 0x3a, 0xc5, 0xbf, 0x0f, 0xe0, 0xeb, 0x4d, 0x0c, 0x79, 0xfc, 0x0d, 0x00, 0x00, 0xff, 0xff,
	0xfb, 0x70, 0xa0, 0x3f, 0x11, 0x01, 0x00, 0x00,
}

func (m *TimedoutPost) Marshal() (dAtA []byte, err error) {
	size := m.Size()
	dAtA = make([]byte, size)
	n, err := m.MarshalToSizedBuffer(dAtA[:size])
	if err != nil {
		return nil, err
	}
	return dAtA[:n], nil
}

func (m *TimedoutPost) MarshalTo(dAtA []byte) (int, error) {
	size := m.Size()
	return m.MarshalToSizedBuffer(dAtA[:size])
}

func (m *TimedoutPost) MarshalToSizedBuffer(dAtA []byte) (int, error) {
	i := len(dAtA)
	_ = i
	var l int
	_ = l
	if len(m.Sender) > 0 {
		i -= len(m.Sender)
		copy(dAtA[i:], m.Sender)
		i = encodeVarintTimedoutPost(dAtA, i, uint64(len(m.Sender)))
		i--
		dAtA[i] = 0x2a
	}
	if len(m.Chain) > 0 {
		i -= len(m.Chain)
		copy(dAtA[i:], m.Chain)
		i = encodeVarintTimedoutPost(dAtA, i, uint64(len(m.Chain)))
		i--
		dAtA[i] = 0x22
	}
	if len(m.Title) > 0 {
		i -= len(m.Title)
		copy(dAtA[i:], m.Title)
		i = encodeVarintTimedoutPost(dAtA, i, uint64(len(m.Title)))
		i--
		dAtA[i] = 0x1a
	}
	if m.Id != 0 {
		i = encodeVarintTimedoutPost(dAtA, i, uint64(m.Id))
		i--
		dAtA[i] = 0x10
	}
	if len(m.Creator) > 0 {
		i -= len(m.Creator)
		copy(dAtA[i:], m.Creator)
		i = encodeVarintTimedoutPost(dAtA, i, uint64(len(m.Creator)))
		i--
		dAtA[i] = 0xa
	}
	return len(dAtA) - i, nil
}

func encodeVarintTimedoutPost(dAtA []byte, offset int, v uint64) int {
	offset -= sovTimedoutPost(v)
	base := offset
	for v >= 1<<7 {
		dAtA[offset] = uint8(v&0x7f | 0x80)
		v >>= 7
		offset++
	}
	dAtA[offset] = uint8(v)
	return base
}
func (m *TimedoutPost) Size() (n int) {
	if m == nil {
		return 0
	}
	var l int
	_ = l
	l = len(m.Creator)
	if l > 0 {
		n += 1 + l + sovTimedoutPost(uint64(l))
	}
	if m.Id != 0 {
		n += 1 + sovTimedoutPost(uint64(m.Id))
	}
	l = len(m.Title)
	if l > 0 {
		n += 1 + l + sovTimedoutPost(uint64(l))
	}
	l = len(m.Chain)
	if l > 0 {
		n += 1 + l + sovTimedoutPost(uint64(l))
	}
	l = len(m.Sender)
	if l > 0 {
		n += 1 + l + sovTimedoutPost(uint64(l))
	}
	return n
}

func sovTimedoutPost(x uint64) (n int) {
	return (math_bits.Len64(x|1) + 6) / 7
}
func sozTimedoutPost(x uint64) (n int) {
	return sovTimedoutPost(uint64((x << 1) ^ uint64((int64(x) >> 63))))
}
func (m *TimedoutPost) Unmarshal(dAtA []byte) error {
	l := len(dAtA)
	iNdEx := 0
	for iNdEx < l {
		preIndex := iNdEx
		var wire uint64
		for shift := uint(0); ; shift += 7 {
			if shift >= 64 {
				return ErrIntOverflowTimedoutPost
			}
			if iNdEx >= l {
				return io.ErrUnexpectedEOF
			}
			b := dAtA[iNdEx]
			iNdEx++
			wire |= uint64(b&0x7F) << shift
			if b < 0x80 {
				break
			}
		}
		fieldNum := int32(wire >> 3)
		wireType := int(wire & 0x7)
		if wireType == 4 {
			return fmt.Errorf("proto: TimedoutPost: wiretype end group for non-group")
		}
		if fieldNum <= 0 {
			return fmt.Errorf("proto: TimedoutPost: illegal tag %d (wire type %d)", fieldNum, wire)
		}
		switch fieldNum {
		case 1:
			if wireType != 2 {
				return fmt.Errorf("proto: wrong wireType = %d for field Creator", wireType)
			}
			var stringLen uint64
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return io.ErrUnexpectedEOF
				}
				b := dAtA[iNdEx]
				iNdEx++
				stringLen |= uint64(b&0x7F) << shift
				if b < 0x80 {
					break
				}
			}
			intStringLen := int(stringLen)
			if intStringLen < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			postIndex := iNdEx + intStringLen
			if postIndex < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			if postIndex > l {
				return io.ErrUnexpectedEOF
			}
			m.Creator = string(dAtA[iNdEx:postIndex])
			iNdEx = postIndex
		case 2:
			if wireType != 0 {
				return fmt.Errorf("proto: wrong wireType = %d for field Id", wireType)
			}
			m.Id = 0
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return io.ErrUnexpectedEOF
				}
				b := dAtA[iNdEx]
				iNdEx++
				m.Id |= uint64(b&0x7F) << shift
				if b < 0x80 {
					break
				}
			}
		case 3:
			if wireType != 2 {
				return fmt.Errorf("proto: wrong wireType = %d for field Title", wireType)
			}
			var stringLen uint64
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return io.ErrUnexpectedEOF
				}
				b := dAtA[iNdEx]
				iNdEx++
				stringLen |= uint64(b&0x7F) << shift
				if b < 0x80 {
					break
				}
			}
			intStringLen := int(stringLen)
			if intStringLen < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			postIndex := iNdEx + intStringLen
			if postIndex < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			if postIndex > l {
				return io.ErrUnexpectedEOF
			}
			m.Title = string(dAtA[iNdEx:postIndex])
			iNdEx = postIndex
		case 4:
			if wireType != 2 {
				return fmt.Errorf("proto: wrong wireType = %d for field Chain", wireType)
			}
			var stringLen uint64
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return io.ErrUnexpectedEOF
				}
				b := dAtA[iNdEx]
				iNdEx++
				stringLen |= uint64(b&0x7F) << shift
				if b < 0x80 {
					break
				}
			}
			intStringLen := int(stringLen)
			if intStringLen < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			postIndex := iNdEx + intStringLen
			if postIndex < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			if postIndex > l {
				return io.ErrUnexpectedEOF
			}
			m.Chain = string(dAtA[iNdEx:postIndex])
			iNdEx = postIndex
		case 5:
			if wireType != 2 {
				return fmt.Errorf("proto: wrong wireType = %d for field Sender", wireType)
			}
			var stringLen uint64
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return io.ErrUnexpectedEOF
				}
				b := dAtA[iNdEx]
				iNdEx++
				stringLen |= uint64(b&0x7F) << shift
				if b < 0x80 {
					break
				}
			}
			intStringLen := int(stringLen)
			if intStringLen < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			postIndex := iNdEx + intStringLen
			if postIndex < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			if postIndex > l {
				return io.ErrUnexpectedEOF
			}
			m.Sender = string(dAtA[iNdEx:postIndex])
			iNdEx = postIndex
		default:
			iNdEx = preIndex
			skippy, err := skipTimedoutPost(dAtA[iNdEx:])
			if err != nil {
				return err
			}
			if (skippy < 0) || (iNdEx+skippy) < 0 {
				return ErrInvalidLengthTimedoutPost
			}
			if (iNdEx + skippy) > l {
				return io.ErrUnexpectedEOF
			}
			iNdEx += skippy
		}
	}

	if iNdEx > l {
		return io.ErrUnexpectedEOF
	}
	return nil
}
func skipTimedoutPost(dAtA []byte) (n int, err error) {
	l := len(dAtA)
	iNdEx := 0
	depth := 0
	for iNdEx < l {
		var wire uint64
		for shift := uint(0); ; shift += 7 {
			if shift >= 64 {
				return 0, ErrIntOverflowTimedoutPost
			}
			if iNdEx >= l {
				return 0, io.ErrUnexpectedEOF
			}
			b := dAtA[iNdEx]
			iNdEx++
			wire |= (uint64(b) & 0x7F) << shift
			if b < 0x80 {
				break
			}
		}
		wireType := int(wire & 0x7)
		switch wireType {
		case 0:
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return 0, ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return 0, io.ErrUnexpectedEOF
				}
				iNdEx++
				if dAtA[iNdEx-1] < 0x80 {
					break
				}
			}
		case 1:
			iNdEx += 8
		case 2:
			var length int
			for shift := uint(0); ; shift += 7 {
				if shift >= 64 {
					return 0, ErrIntOverflowTimedoutPost
				}
				if iNdEx >= l {
					return 0, io.ErrUnexpectedEOF
				}
				b := dAtA[iNdEx]
				iNdEx++
				length |= (int(b) & 0x7F) << shift
				if b < 0x80 {
					break
				}
			}
			if length < 0 {
				return 0, ErrInvalidLengthTimedoutPost
			}
			iNdEx += length
		case 3:
			depth++
		case 4:
			if depth == 0 {
				return 0, ErrUnexpectedEndOfGroupTimedoutPost
			}
			depth--
		case 5:
			iNdEx += 4
		default:
			return 0, fmt.Errorf("proto: illegal wireType %d", wireType)
		}
		if iNdEx < 0 {
			return 0, ErrInvalidLengthTimedoutPost
		}
		if depth == 0 {
			return iNdEx, nil
		}
	}
	return 0, io.ErrUnexpectedEOF
}

var (
	ErrInvalidLengthTimedoutPost        = fmt.Errorf("proto: negative length found during unmarshaling")
	ErrIntOverflowTimedoutPost          = fmt.Errorf("proto: integer overflow")
	ErrUnexpectedEndOfGroupTimedoutPost = fmt.Errorf("proto: unexpected end of group")
)