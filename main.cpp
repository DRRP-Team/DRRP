#include <iostream>
#include <fstream>
#include <GL/glut.h>
#include <sstream>
#include <vector>

struct bspcolor_t {
    uint8_t b;
    uint8_t g;
    uint8_t r;
};

struct bspheader_t {
    bspcolor_t floorcolor;
    bspcolor_t ceilingcolor;
    bspcolor_t unknown0;
    uint8_t levelid;
    bspcolor_t unknown1;
};

struct linedef_t {
    uint8_t x0;
    uint8_t y0;
    uint8_t x1;
    uint8_t y1;
    uint16_t walltex;
    uint16_t flags;
};

struct mappingsheader_t {
    uint32_t group1size;
    uint32_t group2size;
    uint32_t group3size;
    uint32_t group4size;
};

struct vertex_t {
    uint32_t x;
    uint32_t y;
};

uint16_t count;
linedef_t *lines;
std::string udmfcode;
mappingsheader_t mh;
uint16_t *texmap;

void display() {
    glClearColor(0.0, 0.0, 0.0, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    glLoadIdentity();
    gluOrtho2D(0, 256, 256, 0);
    glColor3f(1.0, 1.0, 1.0);
    glBegin(GL_LINES);
    for(int i = 0; i < count; i++) {
        glVertex2i(lines[i].x0, lines[i].y0);
        glVertex2i(lines[i].x1, lines[i].y1);
    }
    glEnd();
    glutSwapBuffers();
}

uint16_t thingmappings[] {};

int main(int argc, char **argv) {
    glutInit(&argc, argv);
    glutInitWindowPosition(50, 50);
    glutInitWindowSize(256, 256);
    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);
    glutCreateWindow("...");
    glutDisplayFunc(display);
    bspheader_t h;
    std::ifstream mfh("mappings.bin", std::fstream::in | std::fstream::binary);

    mfh.read(reinterpret_cast<char*>(&mh), sizeof(mh));
    mfh.ignore(8 * mh.group1size + 8 * mh.group2size);
    texmap = new uint16_t[mh.group3size];
    mfh.read(reinterpret_cast<char*>(texmap), sizeof(uint16_t) * mh.group3size);
    mfh.close();

    //TODO:
    std::ifstream fh(argv[1], std::fstream::in | std::fstream::binary);
    fh.read(reinterpret_cast<char*>(&h), sizeof(h));
    fh.read(reinterpret_cast<char*>(&count), sizeof(count));

    fh.ignore(count * 10);
    fh.read(reinterpret_cast<char*>(&count), sizeof(count));
    lines = (linedef_t *) malloc(sizeof(linedef_t) * count);
    fh.read(reinterpret_cast<char*>(lines), sizeof(linedef_t) * count);
    //TODO:

    std::stringstream ss;
    ss << "sector {\n";
    ss <<   "\theightceiling = 64;\n";
    ss <<   "\ttexturefloor = \"floor\";\n";
    ss <<   "\ttextureceiling = \"ceiling\";\n";
    ss << "}\n\n";
    for(int i = 0; i < count; i++) {
        ss << "vertex {\n";
        ss <<   "\tx = " << (int) lines[i].x0 * 8 << ";\n";
        ss <<   "\ty = " << (int) lines[i].y0 * 8 << ";\n";
        ss << "}\n\n";
        ss << "vertex {\n";
        ss <<   "\tx = " << (int) lines[i].x1 * 8 << ";\n";
        ss <<   "\ty = " << (int) lines[i].y1 * 8 << ";\n";
        ss << "}\n\n";
        ss << "sidedef {\n";
        ss <<   "\tsector = 0;\n";
        ss <<   "\ttexturemiddle = \"drdc" << texmap[lines[i].walltex] << "\";\n";
        ss << "}\n\n";
        ss << "linedef {\n";
        ss <<   "\tv2 = " << (int) i * 2 << ";\n";
        ss <<   "\tv1 = " << i * 2 + 1 << ";\n";
        ss <<   "\tsidefront = " << (int) i << ";\n";
        ss << "}\n\n";
    }
    std::ofstream ofh(argv[2], std::fstream::trunc);
    ofh << ss.str();
    //std::cout << count << std::endl;

    glutMainLoop();
    return 0;
}
